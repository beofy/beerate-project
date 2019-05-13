package cn.beerate.jpa.repository.support;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericRepositoryFactoryBean<T extends Repository<S, ID>, S, ID> extends TransactionalRepositoryFactoryBeanSupport<T,S,ID> {


    private @Nullable EntityManager entityManager;
    private EntityPathResolver entityPathResolver;
    private EscapeCharacter escapeCharacter = EscapeCharacter.of('\\');

    public GenericRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);

    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void setMappingContext(MappingContext<?, ?> mappingContext) {
        super.setMappingContext(mappingContext);
    }

    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        Assert.state(entityManager != null, "EntityManager must not be null!");

        GenericRepositoryFactory genericRepositoryFactory = new GenericRepositoryFactory(entityManager);
        genericRepositoryFactory.setEntityPathResolver(entityPathResolver);
        genericRepositoryFactory.setEscapeCharacter(escapeCharacter);

        return genericRepositoryFactory;
    }

    @Autowired
    public void setEntityPathResolver(ObjectProvider<EntityPathResolver> resolver) {
        this.entityPathResolver = resolver.getIfAvailable(() -> SimpleEntityPathResolver.INSTANCE);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(entityManager != null, "EntityManager must not be null!");
        super.afterPropertiesSet();
    }

    public void setEscapeCharacter(char escapeCharacter) {

        this.escapeCharacter = EscapeCharacter.of(escapeCharacter);
    }

}
