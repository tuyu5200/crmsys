package com.tuyu.dao.impl;

import com.tuyu.dao.RoleDao;
import com.tuyu.dao.UserDao;
import com.tuyu.dao.support.impl.BaseDaoImpl;
import com.tuyu.po.User;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
}
