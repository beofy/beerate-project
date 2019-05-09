package cn.beerate.service;

import cn.beerate.model.Model;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

/**
 * 基础业务接口
 */
public interface IBaseService<T extends Model> {

    /**
     * 根据主键id查询
     */
    T getOne(long id);

    /**
     * 删除
     */
    T delete(T t);

    /**
     * 修改
     */
    T save(T t);

    /**
     * 简单分页查询
     * <br> 字符类型支持模糊查询
     * <br>非字符类型只能精准查询
     * <br>不支持范围查询
     */
    Page<T> page(int page,int size,String column,String order, Example<T> example);

    /**
     * 复杂分页查询
     * <br> 支持模糊查询
     * <br> 范围查询
     */
    Page<T> page(int page,int size,String column,String order,Specification<T> spec);

    /**
     * 复杂分页查询
     * <br> 支持模糊查询
     * <br> 范围查询
     */
    Page<T> page(int page,int size,String column,String order, Predicate predicate);

}
