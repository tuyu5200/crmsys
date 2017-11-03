package com.tuyu.service.support.impl;

import com.tuyu.dao.support.BaseDao;
import com.tuyu.service.support.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao baseDao;
    
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void save(T t) {
        this.baseDao.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(Integer id) {
        this.baseDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void delete(T t) {
        this.baseDao.delete(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void update(T t) {
        this.baseDao.update(t);
    }

    @Override
    public List<T> queryAll() {
        return this.baseDao.queryAll();
    }

    @Override
    public T queryById(Integer id) {
        return (T) this.baseDao.queryById(id);
    }

    @Override
    public List<T> queryByIds(List<Integer> ids) {
        return this.baseDao.queryByIds(ids);
    }
}
