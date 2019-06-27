package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.ICourseDao;
import com.teaching.domain.Course;
import com.teaching.domain.ResponseModel;
import com.teaching.service.ICourseService;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class CourseServiceImpl implements ICourseService {

    private ICourseDao courseDao = null;

    @Override
    public ResponseModel<Course> getAllCourse() {
        courseDao = DaoFactory.getCourseDao();
        List<Course> allCourse = courseDao.getAllCourse();
        ResponseModel<Course> cModel = ResponseModel.buildSuccess(allCourse.size(), allCourse);
        return cModel;
    }

    @Override
    public ResponseModel<Course> getCourses(String keyWord) {
        courseDao = DaoFactory.getCourseDao();
        List<Course> allCourse = courseDao.getCourses(keyWord);
        ResponseModel<Course> cModel = ResponseModel.buildSuccess(allCourse.size(), allCourse);
        return cModel;
    }

    @Override
    public ResponseModel<Course> getCourses(int currentPage, int perPageSize) {
        courseDao = DaoFactory.getCourseDao();
        int totalNum = courseDao.getCourseCount();
        ResponseModel<Course> courseModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Course> courses = courseDao.getCourses(courseModel.getPageStart(), courseModel.getPageEnd());
        courseModel.setData(courses);
        return courseModel;
    }

    @Override
    public ResponseModel<Course> getCourses(String keyWord, int currentPage, int perPageSize) {
        courseDao = DaoFactory.getCourseDao();
        int totalNum = courseDao.getCourseCount(keyWord);
        ResponseModel<Course> courseModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Course> courses = courseDao.getCourses(keyWord, courseModel.getPageStart(), courseModel.getPageEnd());
        courseModel.setData(courses);
        return courseModel;
    }

    @Override
    public ResponseModel<Course> getTeaCourses(String teaId, String keyWord, int currentPage, int perPageSize) {
        courseDao = DaoFactory.getCourseDao();
        int totalNum = courseDao.getCourseCount(keyWord);
        ResponseModel<Course> courseModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<Course> courses = courseDao.getTeaCourses(teaId,keyWord, courseModel.getPageStart(), courseModel.getPageEnd());
        courseModel.setData(courses);
        return courseModel;
    }

    @Override
    public boolean deleteCourse(String id) {
        courseDao = DaoFactory.getCourseDao();
        int result = courseDao.deleteCourse(id);
        return result > 0;
    }

    @Override
    public boolean insertCourse(Course course) {
        courseDao = DaoFactory.getCourseDao();
        int result = courseDao.insertCourse(course);
        return result > 0;
    }

    @Override
    public boolean updateCourse(Course course) {
        courseDao = DaoFactory.getCourseDao();
        int result = courseDao.updateCourse(course);
        return result > 0;
    }
}
