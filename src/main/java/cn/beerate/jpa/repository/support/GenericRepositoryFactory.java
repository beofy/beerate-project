package cn.beerate.jpa.repository.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.projection.CollectionAwareProjectionFactory;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.jpa.util.JpaMetamodel;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.querydsl.EntityPathResolver;
import cn.beerate.jpa.repository.querydsl.ICustomQueryDsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.QueryCreationListener;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.RepositoryFragment;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

import static org.springframework.data.querydsl.QuerydslUtils.QUERY_DSL_PRESENT;

public class GenericRepositoryFactory extends RepositoryFactorySupport {

    private final EntityManager entityManager;
    private final QueryExtractor extractor;
    private final CrudMethodMetadataPostProcessor crudMethodMetadataPostProcessor;

    private EntityPathResolver entityPathResolver;
    private EscapeCharacter escapeCharacter = EscapeCharacter.of('\\');

    public GenericRepositoryFactory(EntityManager entityManager) {
        Assert.notNull(entityManager, "EntityManager must not be null!");

        this.entityManager = entityManager;
        this.extractor = PersistenceProvider.fromEntityManager(entityManager);
        this.crudMethodMetadataPostProcessor = new CrudMethodMetadataPostProcessor();
        this.entityPathResolver = SimpleEntityPathResolver.INSTANCE;

        addRepositoryProxyPostProcessor(crudMethodMetadataPostProcessor);

        if (extractor.equals(PersistenceProvider.ECLIPSELINK)) {
            addQueryCreationListener(new EclipseLinkProjectionQueryCreationListener(entityManager));
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

        super.setBeanClassLoader(classLoader);
        this.crudMethodMetadataPostProcessor.setBeanClassLoader(classLoader);
    }

    public void setEntityPathResolver(EntityPathResolver entityPathResolver) {
        Assert.notNull(entityPathResolver, "EntityPathResolver must not be null!");

        this.entityPathResolver = entityPathResolver;
    }

    public void setEscapeCharacter(EscapeCharacter escapeCharacter) {
        this.escapeCharacter = escapeCharacter;
    }

    @Override
    protected final JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information) {
        JpaRepositoryImplementation<?, ?> repository = getTargetRepository(information, entityManager);
        repository.setRepositoryMethodMetadata(crudMethodMetadataPostProcessor.getCrudMethodMetadata());

        return repository;
    }

    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information,EntityManager entityManager) {

        JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
        Object repository = getTargetRepositoryViaReflection(information, entityInformation, entityManager);

        Assert.isInstanceOf(JpaRepositoryImplementation.class, repository);

        return (JpaRepositoryImplementation<?, ?>) repository;
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return SimpleJpaRepository.class;
    }

    @Override
    protected ProjectionFactory getProjectionFactory(ClassLoader classLoader, BeanFactory beanFactory) {

        CollectionAwareProjectionFactory factory = new CollectionAwareProjectionFactory();
        factory.setBeanClassLoader(classLoader);
        factory.setBeanFactory(beanFactory);

        return factory;
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(@Nullable QueryLookupStrategy.Key key,QueryMethodEvaluationContextProvider evaluationContextProvider) {
        return Optional.of(JpaQueryLookupStrategy.create(entityManager, key, extractor, evaluationContextProvider, escapeCharacter));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, ID> JpaEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return (JpaEntityInformation<T, ID>) JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
    }

    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
        RepositoryComposition.RepositoryFragments fragments = RepositoryComposition.RepositoryFragments.empty();

        boolean isQueryDslRepository = QUERY_DSL_PRESENT && ICustomQueryDsl.class.isAssignableFrom(metadata.getRepositoryInterface());

        if (isQueryDslRepository) {

            if (metadata.isReactiveRepository()) {
                throw new InvalidDataAccessApiUsageException("Cannot combine QueryDsl and reactive repository support in a single interface");
            }

            JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());

            Object queryDslFragment = getTargetRepositoryViaReflection(GenericQueryDsl.class, entityInformation,entityManager, entityPathResolver, crudMethodMetadataPostProcessor.getCrudMethodMetadata());

            fragments = fragments.append(RepositoryFragment.implemented(queryDslFragment));
        }

        return fragments;
    }


    @Slf4j
    private static class EclipseLinkProjectionQueryCreationListener implements QueryCreationListener<AbstractJpaQuery> {

        private static final String ECLIPSELINK_PROJECTIONS = "Usage of Spring Data projections detected on persistence provider EclipseLink. Make sure the following query methods declare result columns in exactly the order the accessors are declared in the projecting interface or the order of parameters for DTOs:";

        private final JpaMetamodel metamodel;

        private boolean warningLogged = false;

        /**
         * Creates a new {@link JpaRepositoryFactory.EclipseLinkProjectionQueryCreationListener} for the given {@link EntityManager}.
         *
         * @param em must not be {@literal null}.
         */
        public EclipseLinkProjectionQueryCreationListener(EntityManager em) {

            Assert.notNull(em, "EntityManager must not be null!");

            this.metamodel = JpaMetamodel.of(em.getMetamodel());
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.repository.core.support.QueryCreationListener#onCreation(org.springframework.data.repository.query.RepositoryQuery)
         */
        @Override
        public void onCreation(AbstractJpaQuery query) {

            JpaQueryMethod queryMethod = query.getQueryMethod();
            ReturnedType type = queryMethod.getResultProcessor().getReturnedType();

            if (type.isProjecting() && !metamodel.isJpaManaged(type.getReturnedType())) {

                if (!warningLogged) {
                    log.info(ECLIPSELINK_PROJECTIONS);
                    this.warningLogged = true;
                }

                log.info(" - {}", queryMethod);
            }
        }
    }
}
