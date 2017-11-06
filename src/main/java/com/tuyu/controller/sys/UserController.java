package com.tuyu.controller.sys;

import com.tuyu.po.Company;
import com.tuyu.po.Dept;
import com.tuyu.po.User;
import com.tuyu.service.CompanyService;
import com.tuyu.service.DeptService;
import com.tuyu.service.UserService;
import com.tuyu.vo.RoleVo;
import com.tuyu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    private DeptService deptService;
    @Autowired
    private CompanyService companyService;

    private final String REFRESH = "redirect:/sys/user/index.do";

    /**
     * 查询显示所有员工
     *
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/index.do")
    public String index(HttpSession session, Map<String, Object> map) {
        //如果使用session中的user则会产生懒加载的问题，因为在第二次访问时session已经关闭了
        User u = (User) session.getAttribute("_SUBJECT");
        //使用spring管理openSessionInViewFilter必须在同义词请求中
        User user = this.userService.queryById(u.getId());
        //判断当前登陆用户的身份
        if (checkAdmin(user)) {

        } else {

        }
        //得到当前用户对应部门下的所有员工
        Dept dept = user.getDept();
        List<User> users = new ArrayList<>();
        if (Objects.nonNull(dept)) {
            users.addAll(dept.getUsers());
        }
        map.put("users", users);
        return "/sys/users.jsp";
    }

    @RequestMapping("/save.do")
    public String save(User user, Integer deptId, Integer companyId) {
        Dept dept = this.deptService.queryById(deptId);
        Company company = null;
        if (Objects.nonNull(companyId)) {
            company = this.companyService.queryById(companyId);
        }
        user.setDept(dept);
        user.setCompany(company);
        this.userService.save(user);
        return this.REFRESH;
    }

    @RequestMapping("/delete.do")
    public String delete(Integer id) {
        this.userService.delete(id);
        return this.REFRESH;
    }

    @RequestMapping("/update.do")
    public String update(User user, Integer deptId, Integer companyId) {
        Dept dept = this.deptService.queryById(deptId);
        Company company = null;
        if (Objects.nonNull(companyId)) {
            company = this.companyService.queryById(companyId);
        }
        user.setDept(dept);
        user.setCompany(company);
        this.userService.update(user);
        return this.REFRESH;
    }

    @RequestMapping("/queryById.do")
    public @ResponseBody
    UserVo queryByIdWithAjax(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ajax查询用户时id不能为空");
        }
        UserVo userVo = new UserVo(this.userService.queryById(id));
        return userVo;
    }

    @RequestMapping("/queryAllRole.do")
    public @ResponseBody
    List<RoleVo> queryAllRoleWithAjax(Integer userId) {
        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("查询角色时用户id不能为空");
        }
        List<RoleVo> roleVos = this.userService.queryAllRoleVoWithChecked(userId);
        return roleVos;
    }

    @RequestMapping("/allocRole.do")
    public String allocRole(@RequestParam List<Integer> roleIds, Integer userId) {
        if (Objects.isNull(roleIds) || Objects.isNull(userId)) {
            throw new IllegalArgumentException("分配角色时角色id或用户id不能为空");
        }
        this.userService.allocRole(userId, roleIds);
        return this.REFRESH;
    }

    public boolean checkAdmin(User user) {
        if (Objects.equals(user.getUsername(), "admin") || Objects.equals(user.getUsername(), "root")) {
            return true;
        }
        return false;
    }
}
