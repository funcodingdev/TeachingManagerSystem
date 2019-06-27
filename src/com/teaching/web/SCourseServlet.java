package com.teaching.web;

import com.teaching.domain.SCourse;
import com.teaching.domain.Student;
import com.teaching.service.ISCourseService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
@WebServlet("/SCourseServlet")
public class SCourseServlet extends BaseServlet {

    private ISCourseService sCourseService = null;

    /**
     * 选课
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String selectCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        if (teachingTaskNum == null || teachingTaskNum.isEmpty()) {
            return null;
        }
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        SCourse sCourse = new SCourse(teachingTaskNum,stu.getId(),0);
        sCourseService = ServiceFactory.getSCourseService();
        if(sCourseService.insertScInfo(sCourse)){
            return "true";
        }
        return null;
    }

    /**
     * 退课
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String retireCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        if (teachingTaskNum == null || teachingTaskNum.isEmpty()) {
            return null;
        }
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        SCourse sCourse = new SCourse(teachingTaskNum,stu.getId(),0);
        sCourseService = ServiceFactory.getSCourseService();
        if(sCourseService.retireScInfo(sCourse)){
            return "true";
        }
        return null;
    }

}
