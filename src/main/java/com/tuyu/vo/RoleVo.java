package com.tuyu.vo;

import com.tuyu.po.Role;
import com.tuyu.po.RoleConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/5
 * @description：
 */
@Setter
@Getter
public class RoleVo {

    private Integer id;
    private String name;
    private Integer enabled;
    private String description = "";
    private RoleConstant roleConstant = null;
    private String dept = "";
    private String company = "";

    //    当前用户是否选中是否选中
    private Boolean checked = false;

    public RoleVo() {
    }

    public RoleVo(Role role) {
        if (Objects.nonNull(role)) {
            this.id = role.getId();
            this.name = role.getName();
            this.enabled = role.getEnabled();
            this.description = role.getDescription();
            this.roleConstant = role.getRoleConstant();
            if (Objects.nonNull(role.getDept())) {
                this.dept = role.getDept().getName();
            }
            if (Objects.nonNull(role.getCompany())) {
                this.company = role.getCompany().getName();
            }
        }
    }
}
