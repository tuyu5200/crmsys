package com.tuyu.service.impl;

import com.tuyu.dao.RoleDao;
import com.tuyu.po.Role;
import com.tuyu.service.RoleService;
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
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }
}
