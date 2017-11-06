package com.tuyu.service.impl;

import com.tuyu.dao.CompanyDao;
import com.tuyu.dao.DeptDao;
import com.tuyu.po.Company;
import com.tuyu.po.Dept;
import com.tuyu.service.DeptService;
import com.tuyu.service.support.impl.BaseServiceImpl;
import com.tuyu.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Service
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {
    @Autowired
    private CompanyDao companyDao;

    private DeptDao deptDao;

    @Autowired
    public void setDeptDao(DeptDao deptDao) {
        super.setBaseDao(deptDao);
        this.deptDao = deptDao;
    }

    @Override
    public List<DeptVo> queryAllByCompany(Integer companyId) {
        Company company = this.companyDao.queryById(companyId);
        List<DeptVo> deptVos = new ArrayList<>();
        List<Dept> depts = this.deptDao.queryAll();
        for (Dept dept : depts) {
            if (Objects.equals(company, dept.getCompany())) {
                deptVos.add(new DeptVo(dept));
            }
        }

        return deptVos;
    }
}
