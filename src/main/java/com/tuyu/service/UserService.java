package com.tuyu.service;

import com.tuyu.po.User;
import com.tuyu.service.support.BaseService;
import com.tuyu.vo.RoleVo;

import java.util.List;

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

    /**
     * 查询所有的角色信息
     *
     * @param userId
     * @return
     */
    List<RoleVo> queryAllRoleVoWithChecked(Integer userId);

    /**
     * 为指定用户分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     */
    void allocRole(Integer userId, List<Integer> roleIds);
}
