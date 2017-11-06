package com.tuyu.service.impl;

import com.tuyu.dao.RoleDao;
import com.tuyu.dao.UserDao;
import com.tuyu.po.Role;
import com.tuyu.po.User;
import com.tuyu.service.UserService;
import com.tuyu.service.support.impl.BaseServiceImpl;
import com.tuyu.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private RoleDao roleDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Override
    public User queryByUsername(String username) {
        return this.userDao.queryByUsername(username);
    }

    @Override
    public List<RoleVo> queryAllRoleVoWithChecked(Integer userId) {
        List<RoleVo> roleVos = new ArrayList<>();
        User user = this.userDao.queryById(userId);

        List<Role> roles = this.roleDao.queryAll();
        List<Role> roleList = new ArrayList<>(user.getRoles());

        for (Role role : roles) {
            RoleVo roleVo = new RoleVo(role);
            roleVos.add(roleVo);
            //设置选中状态
            if (roleList.contains(role)) {
                roleVo.setChecked(true);
            }
        }
        return roleVos;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void allocRole(Integer userId, List<Integer> roleIds) {
        List<Role> roles = this.roleDao.queryByIds(roleIds);
        User user = this.userDao.queryById(userId);
        //清空原有关系
        user.getRoles().clear();
        //重新建立关系
        user.getRoles().addAll(roles);
        //显示更新一下，如果处于事务中，则不是必须进行显示更新
        this.userDao.update(user);
    }
}
