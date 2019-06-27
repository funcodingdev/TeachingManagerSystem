package com.teaching.service;

import com.teaching.domain.Grade;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Student;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public interface IStudentService {
    ResponseModel<Student> getAllStudent();//获取所有学生

    ResponseModel<Student> getStudents(String keyWord);//模糊查询

    ResponseModel<Student> getStudents(int currentPage, int perPageSize);//获取当前范围的学生

    ResponseModel<Student> getStudents(String keyWord, int currentPage, int perPageSize);//模糊查询

    ResponseModel<Grade> getSCGrade(String teachingTaskNum);//获取选课学生及其成绩

    boolean deleteStudent(String id);//删除学生

    boolean insertStudent(Student stu);//插入学生

    boolean updateStudent(Student stu);//更新学生

    Student findStudent(String id, String password);//查找一个学生
}
