package cn.beerate.service;

import cn.beerate.model.entity.Model;

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

}
