package com.teaching.web;

import com.alibaba.fastjson.JSONArray;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.SCourse;
import com.teaching.domain.Student;
import com.teaching.service.ISCourseService;
import com.teaching.service.ServiceFactory;
import com.teaching.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

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
        String startTime = request.getParameter("startTime");
        if (teachingTaskNum == null || teachingTaskNum.isEmpty()
                || startTime == null || startTime.isEmpty()) {
            return ResponseModel.buildErrorParameter();
        }
        long selectTime = Long.valueOf(startTime);//选课时间
        long currentTime = new Date().getTime();//当前时间
        if(currentTime >= selectTime){//当前时间如果在开课时间之后即选课失败
            return ResponseModel.buildMessage(false,"当前课程已经开课");
        }
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        SCourse sCourse = new SCourse(teachingTaskNum,stu.getId(),0);
        sCourseService = ServiceFactory.getSCourseService();
        if(sCourseService.insertScInfo(sCourse)){
            return ResponseModel.buildMessage(true,"选课成功");
        }else{
            return ResponseModel.buildMessage(false,"选课失败");
        }
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
        String startTime = request.getParameter("startTime");
        if (teachingTaskNum == null || teachingTaskNum.isEmpty()
                || startTime == null || startTime.isEmpty()) {
            return ResponseModel.buildErrorParameter();
        }
        long selectTime = Long.valueOf(startTime);//选课时间
        long currentTime = new Date().getTime();//当前时间
        if(currentTime > selectTime){
            return ResponseModel.buildMessage(false,"当前课程已经开课");
        }
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        SCourse sCourse = new SCourse(teachingTaskNum,stu.getId(),0);
        sCourseService = ServiceFactory.getSCourseService();
        if(sCourseService.retireScInfo(sCourse)){
            return ResponseModel.buildMessage(true,"退选成功");
        }else{
            return ResponseModel.buildMessage(false,"退选失败");
        }
    }

}
