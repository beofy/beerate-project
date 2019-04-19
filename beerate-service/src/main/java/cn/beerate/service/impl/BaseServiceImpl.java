package cn.beerate.service.impl;

import cn.beerate.dao.IBaseDao;
import cn.beerate.model.entity.Model;
import cn.beerate.service.IBaseService;

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
}
