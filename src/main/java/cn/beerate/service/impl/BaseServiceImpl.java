package cn.beerate.service.impl;

import cn.beerate.dao.IBaseDao;
import cn.beerate.model.Model;
import cn.beerate.service.IBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class BaseServiceImpl<T extends Model> implements IBaseService<T> {
    private IBaseDao<T> iBaseDao;

    <D extends IBaseDao<T>> BaseServiceImpl(D d) {
        iBaseDao=d;
    }

    @Override
    public T getOne(long id) {
        return iBaseDao.getOne(id);
    }

    public T getOne(Example<T> example){
        Optional<T> operation = iBaseDao.findOne(example);

        return operation.orElse(null);
    }

    public T getOne(Specification<T> spec){
        Optional<T> operation = iBaseDao.findOne(spec);

        return operation.orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T delete(T t) {
        iBaseDao.delete(t);
        return t;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T save(T t) {
        return iBaseDao.save(t);
    }

    public Page<T> page(int page,int size,String column,String order,Example<T> example){
        if (StringUtils.isBlank(column)||StringUtils.isBlank(order)){
            column="id";
            order="desc";
        }

        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);

        return iBaseDao.findAll(example,pageable);
    }

    public Page<T> page(int page,int size,String column,String order, Specification<T> spec){
        if (StringUtils.isBlank(column)||StringUtils.isBlank(order)){
            column="id";
            order="desc";
        }

        //排序条件
        Sort sort =Sort.by(Sort.Direction.fromString(order),column);
        //分页排序
        Pageable pageable = PageRequest.of(page-1,size,sort);

        return iBaseDao.findAll(spec,pageable);
    }


}