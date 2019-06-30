package com.teaching.dao.impl;

import com.teaching.dao.IDeptDao;
import com.teaching.domain.Department;
import com.teaching.jdbc.handler.BeanHandler;
import com.teaching.jdbc.util.CRUDTemplate;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class DeptDaoImpl implements IDeptDao {
    @Override
    public Department getDepartment(String id) {
        String sql = "select * from department where id = ?";
        return CRUDTemplate.executeQuery(sql,new BeanHandler<>(Department.class),id);
    }
}
