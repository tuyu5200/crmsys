package com.tuyu.vo;

import com.tuyu.po.Resource;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/6
 * @description：
 */
@Setter
@Getter
public class ResourceVo {

    private Integer id;
    private String name = "";
    private boolean enabled = false;
    private Integer parent = 0;

    //设置是否选中
    private boolean checked = false;

    public ResourceVo() {
    }

    public ResourceVo(Resource resource) {
        if (Objects.isNull(resource)) {
            throw new IllegalArgumentException();
        }

        this.id = resource.getId();
        this.name = resource.getName();
        this.enabled = resource.getEnabled() == 1;

    }
}
