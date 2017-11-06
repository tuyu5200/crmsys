package com.tuyu.security;

import com.tuyu.po.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/2
 * @description：
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //判断当前用户是否登陆
        User user = (User) session.getAttribute("_SUBJECT");
        if (Objects.isNull(user)) {
            request.setAttribute("message", "请登陆后在进行操作！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return false;
        }
        //判断权限

        
        return true;
    }
}
