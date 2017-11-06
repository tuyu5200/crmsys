package com.tuyu.vo;

import com.tuyu.po.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Setter
@Getter
public class UserVo {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer sex;
    private Integer enabled;
    private Integer locked;
    private String description;
    private String name;
    private String company = null;
    private String dept = null;

    public UserVo(User user) {
        if (Objects.nonNull(user)) {
            this.username = user.getUsername();
            this.id = user.getId();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.sex = user.getSex();
            this.enabled = user.getEnabled();
            this.locked = user.getLocked();
            this.description = user.getDescription();
            this.name = user.getName();
            if (Objects.nonNull(user.getDept())) {
                this.dept = user.getDept().getName();
            }
            if (Objects.nonNull(user.getCompany())) {
                this.company = user.getCompany().getName();
            }
        }
    }
}
