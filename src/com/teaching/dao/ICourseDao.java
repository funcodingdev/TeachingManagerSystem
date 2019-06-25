package com.teaching.dao;

import com.teaching.domain.Course;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ICourseDao {
    List<Course> getAllCourse();//获取所有的课程

    List<Course> getCourses(String keyWord);//获取模糊查询的课程

    List<Course> getCourses(int start, int end);//获取当前范围的课程

    List<Course> getCourses(String keyWord, Integer pageStart, Integer pageEnd);//获取模糊查询当前范围的课程

    int deleteCourse(String id);//删除课程

    int insertCourse(Course course);//插入课程

    int updateCourse(Course course);//更新课程

    int getCourseCount(String... args);//获取所有课程数量
}
