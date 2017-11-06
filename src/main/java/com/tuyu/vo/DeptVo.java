package com.tuyu.vo;

import com.tuyu.po.Dept;
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
public class DeptVo {
    private Integer id;
    private String name;
    private String description;


    public DeptVo() {
    }

    public DeptVo(Dept dept) {
        if (Objects.isNull(dept)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        this.id = dept.getId();
        this.name = dept.getName();
        this.description = dept.getDescription();
    }
}
