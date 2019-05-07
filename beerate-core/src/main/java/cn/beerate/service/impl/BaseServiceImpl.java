package cn.beerate.service.impl;

import cn.beerate.dao.IBaseDao;
import cn.beerate.model.Model;
import cn.beerate.service.IBaseService;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

public class BaseServiceImpl<T extends Model> implements IBaseService<T> {
    private IBaseDao<T> iBaseDao;

    <D extends IBaseDao<T>> BaseServiceImpl(D d) {
        iBaseDao=d;
    }

    @Override
    public T getOne(long id) {
        return iBaseDao.getOne(id);
    }

    @Override
    public T delete(T t) {
        iBaseDao.delete(t);
        return t;
    }

    @Override
    public T save(T t) {
        return iBaseDao.save(t);
    }

    /**
     * 简单分页查询
     * <br> 字符类型支持模糊查询
     * <br>非字符类型只能精准查询
     * <br>不支持范围查询
     */
    public Page<T> page(int page,int size,String column,String order,Example<T> example){
        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);

        return iBaseDao.findAll(example,pageable);
    }

    /**
     * 复杂分页查询
     * <br> 支持模糊查询
     * <br> 返回查询
     */
    public Page<T> page(int page,int size,String column,String order,@Nullable Specification<T> spec){
        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);
        return iBaseDao.findAll(spec,pageable);
    }

}
