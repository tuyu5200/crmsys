package com.tuyu.service.impl;

import com.tuyu.dao.UserDao;
import com.tuyu.po.User;
import com.tuyu.service.UserService;
import com.tuyu.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Override
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }
}