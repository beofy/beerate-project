package cn.beerate.dao.impl;

import cn.beerate.dao.ICustomQueryDsl;
import cn.beerate.model.Model;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CustomQueryDslDaoImpl implements ICustomQueryDsl {

    private EntityManager entityManager;

    public CustomQueryDslDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <B,T extends Model> Page<B> findAll(Expression<B> expr, EntityPath<T> entityPath, Predicate predicate, Pageable pageable){

        Querydsl querydsl = new Querydsl(entityManager,new PathBuilder<>(entityPath.getType(), entityPath.getMetadata()));

        JPQLQuery<B> jpaQuery = querydsl.createQuery().select(expr).where(predicate);

        querydsl.applyPagination(pageable,jpaQuery).from(entityPath);

        return PageableExecutionUtils.getPage(jpaQuery.fetch(),pageable, jpaQuery::fetchCount);
    }

    public <B,T extends Model> B getOne(Expression<B> expr, EntityPath<T> entityPath, Predicate predicate){

        Querydsl querydsl = new Querydsl(entityManager,new PathBuilder<>(entityPath.getType(), entityPath.getMetadata()));

        JPQLQuery<B> jpaQuery = querydsl.createQuery().select(expr).where(predicate);

        return jpaQuery.from(entityPath).fetchOne();
    }

}
