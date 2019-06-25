package com.teaching.service;

import com.teaching.domain.Admin;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/5/25 15:40
 */
public interface IAdminService {
    List<Admin> getAllAdmin();//获取所有的管理员

    Admin getAdmin(String id);//获取某个管理员

    boolean insertAdmin(Admin admin);//添加管理员

    boolean updateAdmin(Admin admin);//修改管理员

    boolean deleteAdmin(String id);//删除管理员

    Admin findAdmin(String id, String password);//查询是否存在该管理员
}
