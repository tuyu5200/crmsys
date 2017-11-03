package com.tuyu.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Controller
public class IndexController {

    @RequestMapping("/sys/index.do")
    public String index() {
        return "/sys/user/index.do";
    }
}
