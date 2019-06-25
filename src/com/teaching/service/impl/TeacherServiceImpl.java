package com.teaching.service.impl;

import com.teaching.dao.ITeacherDao;
import com.teaching.dao.DaoFactory;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Teacher;
import com.teaching.service.ITeacherService;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class TeacherServiceImpl implements ITeacherService {
    private ITeacherDao teacherDao = null;

    @Override
    public ResponseModel<Teacher> getAllTeacher() {
        teacherDao = DaoFactory.getTeacherDao();
        List<Teacher> allTeacher = teacherDao.getAllTeacher();
        ResponseModel<Teacher> teaModel = ResponseModel.buildSuccess(allTeacher.size(), allTeacher);
        return teaModel;
    }

    @Override
    public ResponseModel<Teacher> getTeachers(String keyWord) {
        teacherDao = DaoFactory.getTeacherDao();
        List<Teacher> allTeacher = teacherDao.getTeachers(keyWord);
        ResponseModel<Teacher> teaModel = ResponseModel.buildSuccess(allTeacher.size(), allTeacher);
        return teaModel;
    }

    @Override
    public ResponseModel<Teacher> getTeachers(int currentPage, int perPageSize) {
        teacherDao = DaoFactory.getTeacherDao();
        int totalNum = teacherDao.getTeaCount();
        ResponseModel<Teacher> teaModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Teacher> students = teacherDao.getTeachers(teaModel.getPageStart(), teaModel.getPageEnd());
        teaModel.setData(students);
        return teaModel;
    }

    @Override
    public ResponseModel<Teacher> getTeachers(String keyWord, int currentPage, int perPageSize) {
        teacherDao = DaoFactory.getTeacherDao();
        int totalNum = teacherDao.getTeaCount(keyWord);
        ResponseModel<Teacher> teaModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Teacher> teachers = teacherDao.getTeachers(keyWord, teaModel.getPageStart(), teaModel.getPageEnd());
        teaModel.setData(teachers);
        return teaModel;
    }

    @Override
    public boolean deleteTeacher(String id) {
        teacherDao = DaoFactory.getTeacherDao();
        int result = teacherDao.deleteTeacher(id);
        return result > 0;
    }

    @Override
    public boolean insertTeacher(Teacher tea) {
        teacherDao = DaoFactory.getTeacherDao();
        int result = teacherDao.insertTeacher(tea);
        return result > 0;
    }

    @Override
    public boolean updateTeacher(Teacher tea) {
        teacherDao = DaoFactory.getTeacherDao();
        int result = teacherDao.updateTeacher(tea);
        return result > 0;
    }

    @Override
    public Teacher findTeacher(String id, String password) {
        teacherDao = DaoFactory.getTeacherDao();
        Teacher teacher = teacherDao.findTeacher(id, password);
        return teacher;
    }
}
