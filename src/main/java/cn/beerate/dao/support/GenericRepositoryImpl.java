package cn.beerate.dao.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 通用的sql查询接口
 */
@Repository
public class GenericRepositoryImpl implements GenericRepository {
    private EntityManager entityManager;

    @Autowired
    public GenericRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * 根据sql获取自定对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public <B> B getObject(String sql, Map<String, Object> args, Class<B> bClass) {
        Query nativeQuery = entityManager.createNativeQuery(sql, bClass);
        setParameter(nativeQuery,args);

        return (B) nativeQuery.getSingleResult();
    }

    /**
     * 统计条数
     */
    @Override
    public Long getCount(String sql, Map<String, Object> args) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        setParameter(nativeQuery,args);

        return ((BigInteger)nativeQuery.getSingleResult()).longValue() ;
    }

    /**
     * 根据sql获取自定对象集合
     */
    @Override
    @SuppressWarnings("unchecked")
    public <B> List<B> getList(String sql,Map<String, Object> args,Class<B> bClass) {
        Query nativeQuery = entityManager.createNativeQuery(sql, bClass);
        setParameter(nativeQuery,args);

        return (List<B>) nativeQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <B> Page<B> getPage(String querySql, String countSql, Map<String, Object> args, Pageable pageable, Class<B> bClass) {
        Query nativeQuery = entityManager.createNativeQuery(querySql, bClass);
        setParameter(nativeQuery,args);

        //排序条件
        Sort sort =pageable.getSort();
        if (sort.isSorted()){
            Optional<Sort.Order> optional = sort.get().findFirst();
            if (optional.isPresent()){
                Sort.Order order =optional.get();
                querySql=querySql+" ORDER BY "+order.getProperty()+" "+order.getDirection().name();
            }
        }

        //分页参数
        if (pageable.isPaged()) {
            nativeQuery.setFirstResult((int) pageable.getOffset());
            nativeQuery.setMaxResults(pageable.getPageSize());
        }

        return pageable.isUnpaged()?new PageImpl<>(getList(querySql,args,bClass)): PageableExecutionUtils.getPage((List<B>)nativeQuery.getResultList(),pageable,() -> getCount(countSql,args));
    }

    private void setParameter(Query query ,Map<String,Object> args){
        if ( (args != null) && (args.keySet().size() > 0) ) {
            Set<Map.Entry<String, Object>> entries = args.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

}
