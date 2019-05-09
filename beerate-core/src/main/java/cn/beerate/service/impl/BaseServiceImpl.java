package cn.beerate.service.impl;

import cn.beerate.dao.IBaseDao;
import cn.beerate.model.Model;
import cn.beerate.service.IBaseService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

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

    public Page<T> page(int page,int size,String column,String order,Example<T> example){
        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);

        return iBaseDao.findAll(example,pageable);
    }

    public Page<T> page(int page,int size,String column,String order, Specification<T> spec){
        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);

        return iBaseDao.findAll(spec,pageable);
    }

    public Page<T> page(int page,int size,String column,String order, Predicate predicate){
        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);

        return iBaseDao.findAll(predicate,pageable);
    }

}
