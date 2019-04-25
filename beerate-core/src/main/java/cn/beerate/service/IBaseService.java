package cn.beerate.service;

import cn.beerate.model.entity.Model;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

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

    Page<T> page(int page,int size,String colum,String order, Example<T> example);

    Page<T> page(int page,int size,String colum,String order,@Nullable Specification<T> spec);

}
