package com.teaching.dao;

import com.teaching.dao.impl.*;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class DaoFactory {
    public static IStudentDao getStudentDao(){
        return new StudentDaoImpl();
    }
    public static ISClassDao getSClassDao(){
        return new SClassDaoImpl();
    }

    public static IDeptDao getDeptDao() {
        return new DeptDaoImpl();
    }

    public static ITeacherDao getTeacherDao() {
        return new TeacherDaoImpl();
    }

    public static ICourseDao getCourseDao() {
        return new CourseImpl();
    }

    public static IAdminDao getAdminDao() {
        return new AdminDaoImpl();
    }

    public static ITeachingTaskDao getTeachingTaskDao() {
        return new TeachingTaskImpl();
    }

    public static ISCourseDao getSCourseDao() {
        return new SCourseDaoImpl();
    }
}
