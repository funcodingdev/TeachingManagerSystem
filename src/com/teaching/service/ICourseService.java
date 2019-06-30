package com.teaching.service;

import com.teaching.domain.Course;
import com.teaching.domain.ResponseModel;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ICourseService {
    ResponseModel<Course> getAllCourses();//获取所有课程

    ResponseModel<Course> getCourses(String keyWord);//获取模糊查询的课程

    ResponseModel<Course> getCourses(int currentPage, int perPageSize);//获取当前范围的课程

    ResponseModel<Course> getCourses(String keyWord, int currentPage, int perPageSize);//模糊查询

    ResponseModel<Course> getCoursesToTea(String teaId, String keyWord, int currentPage, int perPageSize);//获取教师所教课程

    boolean deleteCourse(String id);//删除课程

    boolean insertCourse(Course course);//插入课程

    boolean updateCourse(Course course);//更新课程
}
