package com.tuyu.controller.sys;

import com.tuyu.po.Dept;
import com.tuyu.po.User;
import com.tuyu.service.DeptService;
import com.tuyu.vo.DeptVo;
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
@RequestMapping("/sys/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    private final String REFRESH = "redirect:/sys/dept/queryAll.do";

    /**
     * 查询当前登陆用户所属公司下的所有部门信息并放入作用域中
     *
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/queryAll.do")
    public String queryAll(Map<String, Object> map, HttpSession session) {
        List<Dept> depts = this.deptService.queryAll();
        List<Dept> deptList = new ArrayList<>();
        User user = (User) session.getAttribute("_SUBJECT");
        for (Dept dept : depts) {
            if (Objects.equals(user.getCompany(), dept.getCompany())) {
                deptList.add(dept);
            }
        }
        map.put("depts", deptList);
        return "/sys/depts.jsp";
    }

    /**
     * ajax查询所有指定公司下的所有部门信息
     *
     * @param companyId
     * @return
     */
    @RequestMapping("/queryAllByCompany.do")
    public @ResponseBody
    List<DeptVo> queryAllByCompany(Integer companyId) {
        if (Objects.isNull(companyId)) {
            throw new IllegalArgumentException("参数不能为空");
        }

        List<DeptVo> deptVoList = this.deptService.queryAllByCompany(companyId);
        return deptVoList;
    }

    @RequestMapping("/queryById.do")
    public @ResponseBody
    DeptVo queryById(Integer deptId) {
        if (Objects.isNull(deptId)) {
            throw new IllegalArgumentException();
        }
        return new DeptVo(this.deptService.queryById(deptId));
    }

    @RequestMapping("/save.do")
    public String save(Dept dept, HttpSession session) {
        if (Objects.isNull(dept)) {
            throw new IllegalArgumentException();
        }
        User user = (User) session.getAttribute("_SUBJECT");
        dept.setCompany(user.getCompany());
        this.deptService.save(dept);

        return this.REFRESH;
    }

    @RequestMapping("/update.do")
    public String update(Dept dept, HttpSession session) {
        if (Objects.isNull(dept)) {
            throw new IllegalArgumentException();
        }
        User user = (User) session.getAttribute("_SUBJECT");
        dept.setCompany(user.getCompany());
        this.deptService.update(dept);

        return this.REFRESH;
    }

    @RequestMapping("/delete.do")
    public String delete(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException();
        }
        this.deptService.delete(id);
        return this.REFRESH;
    }
}
