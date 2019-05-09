package cn.beerate.dao;

import cn.beerate.model.Model;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 自定义QueryDsl查询接口
 */
public interface ICustomQueryDsl {

    <B,T extends Model> Page<B> findAll(Expression<B> expr, EntityPath<T> entityPath, Predicate predicate, Pageable pageable);

}
