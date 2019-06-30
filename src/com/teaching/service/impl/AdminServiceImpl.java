package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.IAdminDao;
import com.teaching.domain.Admin;
import com.teaching.service.IAdminService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class AdminServiceImpl implements IAdminService {

    private IAdminDao adminDao;

    @Override
    public List<Admin> getAllAdmins() {
        return null;
    }

    @Override
    public Admin getAdmin(String id) {
        return null;
    }

    @Override
    public boolean insertAdmin(Admin admin) {
        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return false;
    }

    @Override
    public boolean deleteAdmin(String id) {
        return false;
    }

    @Override
    public Admin findAdmin(String id, String password) {
        adminDao = DaoFactory.getAdminDao();
        return adminDao.findAdmin(id, password);
    }
}
