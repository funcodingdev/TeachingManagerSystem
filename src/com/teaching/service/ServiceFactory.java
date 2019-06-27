package com.teaching.service;

import com.teaching.service.impl.*;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class ServiceFactory {
    public static IStudentService getStudentService() {
        return new StudentServiceImpl();
    }

    public static ISClassService getSClassService() {
        return new SClassServiceImpl();
    }

    public static IDeptService getDeptService() {
        return new DeptServiceImpl();
    }

    public static ITeacherService getTeacherService() {
        return new TeacherServiceImpl();
    }

    public static ICourseService getCourseService() {
        return new CourseServiceImpl();
    }

    public static IAdminService getAdminService() {
        return new AdminServiceImpl();
    }

    public static ITeachingTaskService getTeachingTaskService() {
        return new TeachingTaskServiceImpl();
    }

    public static ISCourseService getSCourseService() {
        return new ISCourseServiceImpl();
    }
}
