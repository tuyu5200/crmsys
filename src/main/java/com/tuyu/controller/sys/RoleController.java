package com.tuyu.controller.sys;

import com.tuyu.po.Role;
import com.tuyu.po.User;
import com.tuyu.service.CompanyService;
import com.tuyu.service.DeptService;
import com.tuyu.service.RoleService;
import com.tuyu.service.UserService;
import com.tuyu.vo.ResourceVo;
import com.tuyu.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/4
 * @description：
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;
    private final String REFRESH = "redirect:/sys/role/queryAll.do";

    /**
     * 得到当前登陆用户所在公司下的所有角色信息
     *
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/queryAll.do")
    public String queryAll(Map<String, Object> map, HttpSession session) {
        List<Role> roles = this.roleService.queryAll();
        map.put("roles", roles);
        return "/sys/roles.jsp";
    }

    @RequestMapping("/save.do")
    public String save(Role role, Integer deptId, Integer companyId) {
        if (Objects.isNull(role)) {
            throw new IllegalArgumentException();
        }
        role.setCompany(this.companyService.queryById(companyId));
        role.setDept(this.deptService.queryById(deptId));
        this.roleService.save(role);
        return this.REFRESH;
    }

    @RequestMapping("/update.do")
    public String update(Role role, Integer deptId, HttpSession session) {
        if (Objects.isNull(role) || Objects.isNull(deptId)) {
            throw new IllegalArgumentException();
        }
        User u = (User) session.getAttribute("_SUBJECT");
        role.setCompany(u.getCompany());
        role.setDept(this.deptService.queryById(deptId));
        this.roleService.update(role);
        return this.REFRESH;
    }

    @RequestMapping("/delete.do")
    public String delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException();
        }
        this.roleService.delete(id);
        return this.REFRESH;
    }

    @RequestMapping("/queryById.do")
    public @ResponseBody
    RoleVo queryById(Integer roleId) {
        if (Objects.isNull(roleId)) {
            throw new IllegalArgumentException();
        }
        RoleVo role = new RoleVo(this.roleService.queryById(roleId));
        return role;
    }

    /**
     * 为角色分配资源
     *
     * @return
     */
    @RequestMapping("/allocResource.do")
    public String allocResource() {


        return this.REFRESH;
    }

    /**
     * ajax查询所有资源并查看当前用户选中的资源
     *
     * @param roleId
     * @return
     */
    public List<ResourceVo> queryAllResource(Integer roleId) {
        List<ResourceVo> resourceVos = new ArrayList<>();


        return resourceVos;
    }
}
