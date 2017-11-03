package com.tuyu.controller.sys;

import com.tuyu.po.User;
import com.tuyu.service.UserService;
import com.tuyu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询显示所有员工
     *
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Map<String, Object> map) {
        User user = (User) session.getAttribute("_SUBJECT");
        //判断当前登陆用户的身份

        //得到当前用户对应部门下的所有员工
        List<User> users = new ArrayList<>(user.getDept().getUsers());
        map.put("users", users);
        return "/index.jsp";
    }

    @RequestMapping("/save.do")
    public String save(User user) {
        this.userService.save(user);
        return "/index.do";
    }

    @RequestMapping("/delete.do")
    public String delete(Integer id) {
        this.userService.delete(id);
        return "/index.do";
    }

    @RequestMapping("/update.do")
    public String update(User user) {
        this.userService.update(user);
        return "/index.do";
    }

    @RequestMapping("/queryById.do")
    public @ResponseBody
    UserVo queryByIdWithAjax(Integer id) {
        UserVo userVo = new UserVo(this.userService.queryById(id));
        return userVo;
    }
}
