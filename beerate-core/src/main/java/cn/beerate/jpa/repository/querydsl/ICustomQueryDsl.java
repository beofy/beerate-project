package cn.beerate.jpa.repository.querydsl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 自定义QueryDsl查询接口
 */
public interface ICustomQueryDsl<T> extends QuerydslPredicateExecutor<T> {

    <B> Page<B> findAll(Expression<B> expr, EntityPath<T> entityPath, Predicate predicate, Pageable pageable);

    <B> B getOne(Expression<B> expr, EntityPath<T> entityPath, Predicate predicate);

}
