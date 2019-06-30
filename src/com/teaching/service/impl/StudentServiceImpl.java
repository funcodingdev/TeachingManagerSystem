package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.IStudentDao;
import com.teaching.domain.Grade;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Student;
import com.teaching.service.IStudentService;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class StudentServiceImpl implements IStudentService {

    IStudentDao studentDao = null;

    @Override
    public ResponseModel<Student> getAllStudent() {
        studentDao = DaoFactory.getStudentDao();
        List<Student> allStudent = studentDao.listAllStudents();
        ResponseModel<Student> model = ResponseModel.buildModelSuccess(allStudent.size(), allStudent);
        return model;
    }

    @Override
    public ResponseModel<Student> getStudents(String keyWord) {
        studentDao = DaoFactory.getStudentDao();
        List<Student> students = studentDao.listStudents(keyWord);
        ResponseModel<Student> model = ResponseModel.buildModelSuccess(students.size(), students);
        return model;
    }

    @Override
    public ResponseModel<Student> getStudents(int currentPage, int perPageSize) {
        studentDao = DaoFactory.getStudentDao();
        int totalNum = studentDao.getStuCount();
        ResponseModel<Student> stuModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Student> students = studentDao.listStudents(stuModel.getPageStart(), stuModel.getPageEnd());
        stuModel.setData(students);
        return stuModel;
    }

    @Override
    public ResponseModel<Student> getStudents(String keyWord, int currentPage, int perPageSize) {
        studentDao = DaoFactory.getStudentDao();
        int totalNum = studentDao.getStuCount(keyWord);
        ResponseModel<Student> stuModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Student> students = studentDao.listStudents(keyWord, stuModel.getPageStart(), stuModel.getPageEnd());
        stuModel.setData(students);
        return stuModel;
    }

    @Override
    public ResponseModel<Grade> getSCGrade(String teachingTaskNum) {
        studentDao = DaoFactory.getStudentDao();
        List<Grade> students = studentDao.getSCGrade(teachingTaskNum);
        return ResponseModel.buildModelSuccess(students.size(),students);
    }

    @Override
    public boolean updateSCGrade(Grade grade) {
        if(grade.getGrade().intValue() < 0 || grade.getGrade().intValue() > 100){
            return false;
        }
        studentDao = DaoFactory.getStudentDao();
        int result = studentDao.updateSCGrade(grade);
        return result > 0;
    }

    @Override
    public boolean deleteStudent(String id) {
        studentDao = DaoFactory.getStudentDao();
        int result = studentDao.deleteStudent(id);
        return result > 0;
    }

    @Override
    public boolean insertStudent(Student stu) {
        studentDao = DaoFactory.getStudentDao();
        int result = studentDao.insertStudent(stu);
        return result > 0;
    }

    @Override
    public boolean updateStudent(Student stu) {
        studentDao = DaoFactory.getStudentDao();
        int result = studentDao.updateStudent(stu);
        return result > 0;
    }

    @Override
    public boolean updateStuPassword(String stuId, String oldPassword, String newPassword) {
        studentDao = DaoFactory.getStudentDao();
        int result = studentDao.updateStuPassword(stuId, oldPassword, newPassword);
        return result > 0;
    }

    @Override
    public Student findStudent(String id, String password) {
        studentDao = DaoFactory.getStudentDao();
        Student student = studentDao.findStudent(id, password);
        return student;
    }
}
