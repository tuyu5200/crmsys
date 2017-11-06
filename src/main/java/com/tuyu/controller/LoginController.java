package com.tuyu.controller;

import com.tuyu.po.User;
import com.tuyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description： 用户登陆和退出
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request, HttpSession session) {
        if (Objects.isNull(username) || Objects.isNull(password)) {
            request.setAttribute("message", "用户名或密码不能为空");
            return "forward:login.jsp";
        }
        User user = this.userService.queryByUsername(username);
        if (Objects.isNull(user)) {
            request.setAttribute("message", "用户名或密码不正确");
            return "forward:login.jsp";
        }
        if (!Objects.equals(user.getPassword(), password)) {
            request.setAttribute("message", "用户名或密码不正确");
            return "forward:login.jsp";
        }

        //登陆成功，将用户信息放入session中
        session.setAttribute("_SUBJECT", user);
        return "redirect:/sys/index.do";
    }

    /**
     * 安全退出
     *
     * @return
     */
    @RequestMapping(value = "logout.do")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("_SUBJECT");
        if (Objects.nonNull(user)) {
            session.invalidate();
        }
        return "redirect:/login.jsp";
    }
}
