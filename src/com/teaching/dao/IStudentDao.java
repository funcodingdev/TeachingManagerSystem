package com.teaching.dao;

import com.teaching.domain.Student;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public interface IStudentDao {
    List<Student> getAllStudent();//获取所有学生

    List<Student> getStudents(String keyWord);//模糊查询

    List<Student> getStudents(int start, int end);//获取当前范围的学生

    List<Student> getStudents(String keyWord, Integer pageStart, Integer pageEnd);//获取模糊查询当前范围的学生

    List<Student> getSCStudent(String teachingTaskNum);//获取选课学生

    int deleteStudent(String id);//删除学生

    int insertStudent(Student stu);//插入学生

    int updateStudent(Student stu);//更新学生

    Student findStudent(String id, String password);//查找一个学生

    int getStuCount(String... args);//获取所有学生数量

}
