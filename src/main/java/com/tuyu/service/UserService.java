package com.tuyu.service;

import com.tuyu.po.User;
import com.tuyu.service.support.BaseService;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
public interface UserService extends BaseService<User> {
    /**
     * 通过用户名查询用户，用于登陆
     *
     * @param username 用户名
     * @return User
     */
    User queryByUsername(String username);
}
