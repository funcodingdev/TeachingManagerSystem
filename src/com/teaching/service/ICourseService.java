package com.teaching.service;

import com.teaching.domain.Course;
import com.teaching.domain.ResponseModel;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ICourseService {
    ResponseModel<Course> getAllCourse();//获取所有课程

    ResponseModel<Course> getCourses(String keyWord);//获取模糊查询的课程

    ResponseModel<Course> getCourses(int currentPage, int perPageSize);//获取当前范围的课程

    ResponseModel<Course> getCourses(String keyWord, int currentPage, int perPageSize);//模糊查询

    boolean deleteCourse(String id);//删除课程

    boolean insertCourse(Course course);//插入课程

    boolean updateCourse(Course course);//更新课程
}
