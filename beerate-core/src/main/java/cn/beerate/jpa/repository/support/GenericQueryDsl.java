package cn.beerate.jpa.repository.support;

import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.EntityPathResolver;
import cn.beerate.jpa.repository.querydsl.ICustomQueryDsl;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;

public class GenericQueryDsl<T> extends QuerydslJpaPredicateExecutor<T> implements ICustomQueryDsl<T> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityPath<T> entityPath;
    private final EntityManager entityManager;

    public GenericQueryDsl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager,EntityPathResolver resolver, @Nullable CrudMethodMetadata metadata) {
        super(entityInformation,entityManager,resolver,metadata);
        this.entityInformation = entityInformation;
        this.entityPath = resolver.createPath(entityInformation.getJavaType());
        this.entityManager = entityManager;
    }

    public <B> Page<B> findAll(Expression<B> expr, Predicate predicate, Pageable pageable){

        Querydsl querydsl = new Querydsl(entityManager,new PathBuilder<>(entityPath.getType(), entityPath.getMetadata()));

        JPQLQuery<B> jpaQuery = querydsl.createQuery().select(expr).where(predicate);

        querydsl.applyPagination(pageable,jpaQuery).from(entityPath);

        return PageableExecutionUtils.getPage(jpaQuery.fetch(),pageable, jpaQuery::fetchCount);
    }

    public <B> B getOne(Expression<B> expr, Predicate predicate){

        Querydsl querydsl = new Querydsl(entityManager,new PathBuilder<>(entityPath.getType(), entityPath.getMetadata()));

        JPQLQuery<B> jpaQuery = querydsl.createQuery().select(expr).where(predicate);

        return jpaQuery.from(entityPath).fetchOne();
    }

}
