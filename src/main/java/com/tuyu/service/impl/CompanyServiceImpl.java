package com.tuyu.service.impl;

import com.tuyu.dao.CompanyDao;
import com.tuyu.po.Company;
import com.tuyu.service.CompanyService;
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
@Transactional(readOnly = true, rollbackFor = Throwable.class)
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        super.setBaseDao(companyDao);
        this.companyDao = companyDao;
    }
    
}
