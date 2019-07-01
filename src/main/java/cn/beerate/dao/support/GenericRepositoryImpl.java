package cn.beerate.dao.support;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
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
        //排序条件
        Sort sort =pageable.getSort();
        if (sort.isSorted()){
            Optional<Sort.Order> optional = sort.get().findFirst();
            if (optional.isPresent()){
                Sort.Order order =optional.get();
                querySql=querySql+" ORDER BY "+order.getProperty()+" "+order.getDirection().name();
            }
        }

        Query nativeQuery = entityManager.createNativeQuery(querySql, bClass);
        setParameter(nativeQuery,args);

        //分页参数
        if (pageable.isPaged()) {
            nativeQuery.setFirstResult((int) pageable.getOffset());
            nativeQuery.setMaxResults(pageable.getPageSize());
        }

        return pageable.isUnpaged()?new PageImpl<>((List<B>)nativeQuery.getResultList()): PageableExecutionUtils.getPage((List<B>)nativeQuery.getResultList(),pageable,() -> getCount(countSql,args));
    }

    private void setParameter(Query query ,Map<String,Object> args){
        query.getParameters().forEach(parameter -> {
             String arg = parameter.getName();
             query.setParameter(arg,args.get(arg));
        });
    }

    @Override
    public Map<String, Object> getMap(String sql, Map<String, Object> args) {
        Query nativeQuery = entityManager.createNativeQuery(sql);
        setParameter(nativeQuery,args);
        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        @SuppressWarnings("unchecked")
        Map<String, Object> objectMap = (Map<String, Object>)nativeQuery.getSingleResult();

        return  objectMap;
    }

    public List<Map<String, Object>> getListMap(String sql, Map<String, Object> args){
        Query nativeQuery = entityManager.createNativeQuery(sql);
        setParameter(nativeQuery,args);

        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> listMapResults =(List<Map<String, Object>>)nativeQuery.getResultList();

        return listMapResults;
    }


}
