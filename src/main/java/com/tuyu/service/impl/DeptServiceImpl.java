package com.tuyu.service.impl;

import com.tuyu.dao.DeptDao;
import com.tuyu.po.Dept;
import com.tuyu.service.DeptService;
import com.tuyu.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
@Service
@Transactional(readOnly = true,rollbackFor = Throwable.class)
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private DeptDao deptDao;
    @Autowired
    public void setDeptDao(DeptDao deptDao) {
        super.setBaseDao(deptDao);
        this.deptDao = deptDao;
    }
}
