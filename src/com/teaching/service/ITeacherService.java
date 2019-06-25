package com.teaching.service;

import com.teaching.domain.ResponseModel;
import com.teaching.domain.Teacher;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ITeacherService {
    ResponseModel<Teacher> getAllTeacher();//获取所有的教师实体

    ResponseModel<Teacher> getTeachers(String keyWord);//模糊查询

    ResponseModel<Teacher> getTeachers(int currentPage, int perPageSize);//获取当前范围的教师

    ResponseModel<Teacher> getTeachers(String keyWord, int currentPage, int perPageSize);//模糊查询

    boolean deleteTeacher(String id);//删除教师

    boolean insertTeacher(Teacher tea);//插入教师

    boolean updateTeacher(Teacher tea);//更新教师

    Teacher findTeacher(String id, String password);//查找一个教师
}
