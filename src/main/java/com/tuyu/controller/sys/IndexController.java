package com.tuyu.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Controller
public class IndexController {

    @RequestMapping("/sys/index.do")
    public ModelAndView index() {
        return new ModelAndView("/index.jsp");
    }
    
}
