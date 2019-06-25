package com.teaching.dao;

import com.teaching.domain.Admin;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/5/25 15:40
 */
public interface IAdminDao {
    List<Admin> getAllAdmin();//获取所有的管理员

    Admin getAdmin(String id);//获取某个管理员

    int insertAdmin(Admin admin);//添加管理员

    int updateAdmin(Admin admin);//修改管理员

    int deleteAdmin(String id);//删除管理员

    Admin findAdmin(String id, String password);//查询是否存在该管理员
}
