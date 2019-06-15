package cn.beerate.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 通用的sql查询接口
 */
public interface GenericDao {

    /**
     * 根据sql获取自定对象
     */
    <B> B getObject(String sql, Map<String, Object> args, Class<B> bClass);

    /**
     * 统计条数
     */
    Long getCount(String sql, Map<String, Object> args);

    /**
     * 根据sql获取自定对象集合
     */
    <B> List<B> getList(String sql,Map<String, Object> args, Class<B> bClass);

    /**
     * 根据sql获取自定分页对象
     */
    <B> Page<B> getPage(String querySql, String countSql, Map<String, Object> args, Pageable pageable, Class<B> bClass);

}
