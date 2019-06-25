package com.teaching.dao.impl;

import com.teaching.dao.ISClassDao;
import com.teaching.domain.SClass;
import com.teaching.jdbc.handler.BeanListHandler;
import com.teaching.jdbc.util.CRUDTemplate;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class SClassDaoImpl implements ISClassDao {
    @Override
    public List<SClass> getAllClass(String deptId) {
        String sql = "select sclass.* from department inner join sclass on department.name = sclass.dept where id = ?";
        return CRUDTemplate.executeQuery(sql,new BeanListHandler<>(SClass.class),deptId);
    }
}
