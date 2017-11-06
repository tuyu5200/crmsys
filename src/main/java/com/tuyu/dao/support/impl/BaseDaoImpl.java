package com.tuyu.dao.support.impl;

import com.tuyu.dao.support.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<?> clazz;

    /**
     * 获取泛型模板的类型信息
     */
    protected BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] typeArguments = type.getActualTypeArguments();
        if (typeArguments.length != 1) {
            throw new IllegalArgumentException("泛型参数不正确");
        }
        this.clazz = (Class<?>) typeArguments[0];
    }


    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(Integer id) {
        getSession().delete(getSession().get(this.clazz, id));
    }

    @Override
    public void delete(T t) {
        getSession().delete(t);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public List<T> queryAll() {
        return getSession().createCriteria(this.clazz).list();
    }

    @Override
    public T queryById(Integer id) {
        return (T) getSession().get(this.clazz, id);
    }

    @Override
    public List<T> queryByIds(List<Integer> ids) {
        return getSession().createCriteria(this.clazz).add(Restrictions.in("id", ids)).list();
    }
}
