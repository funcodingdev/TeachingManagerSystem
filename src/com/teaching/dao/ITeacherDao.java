package com.teaching.dao;

import com.teaching.domain.Teacher;
import com.teaching.domain.Teacher;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ITeacherDao {
    List<Teacher> listAllTeachers();//获取所有教师

    List<Teacher> listTeachers(String keyWord);//获取模糊查询的教师

    List<Teacher> listTeachers(int start, int end);//获取当前范围的教师

    List<Teacher> listTeachers(String keyWord, Integer pageStart, Integer pageEnd);//获取模糊查询当前范围的教师

    int deleteTeacher(String id);//删除教师

    int insertTeacher(Teacher tea);//插入教师

    int updateTeacher(Teacher tea);//更新教师

    Teacher findTeacher(String id, String password);//查找一个教师

    int getTeaCount(String... args);//获取所有教师数量
}
