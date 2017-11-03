package com.tuyu.dao;

import com.tuyu.dao.support.BaseDao;
import com.tuyu.po.User;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
public interface UserDao extends BaseDao<User> {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return User
     */
    User queryByUsername(String username);
}
