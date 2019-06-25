package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.IDeptDao;
import com.teaching.domain.Department;
import com.teaching.service.IDeptService;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class DeptServiceImpl implements IDeptService {
    private IDeptDao deptDao;
    @Override
    public Department getDeptName(String id) {
        deptDao = DaoFactory.getDeptDao();
        Department department = deptDao.getDeptName(id);
        return department;
    }
}
