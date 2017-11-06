package com.tuyu.service;

import com.tuyu.po.Dept;
import com.tuyu.service.support.BaseService;
import com.tuyu.vo.DeptVo;

import java.util.List;

/**
 * @author walker tu
 * @date 2017/11/3
 * @description：
 */
public interface DeptService extends BaseService<Dept> {
    /**
     * 查询指定公司下所有的部门
     *
     * @param companyId 公司id
     * @return
     */
    List<DeptVo> queryAllByCompany(Integer companyId);
}
