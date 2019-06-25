package com.teaching.dao.impl;

import com.teaching.dao.IAdminDao;
import com.teaching.domain.Admin;
import com.teaching.jdbc.handler.BeanHandler;
import com.teaching.jdbc.util.CRUDTemplate;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/5/25 15:43
 */
public class AdminDaoImpl implements IAdminDao {

    @Override
    public List<Admin> getAllAdmin() {
        return null;
    }

    @Override
    public Admin getAdmin(String id) {
        return null;
    }

    @Override
    public int insertAdmin(Admin admin) {
        return 0;
    }

    @Override
    public int updateAdmin(Admin admin) {
        return 0;
    }

    @Override
    public int deleteAdmin(String id) {
        return 0;
    }

    @Override
    public Admin findAdmin(String id, String password) {
        String sql = "select * from admin where id = ? and password = ?";
        return CRUDTemplate.executeQuery(sql,new BeanHandler<>(Admin.class),id,password);
    }
}
