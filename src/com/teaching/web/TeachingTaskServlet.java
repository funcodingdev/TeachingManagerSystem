package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Student;
import com.teaching.domain.Teacher;
import com.teaching.domain.TeachingTask;
import com.teaching.service.ITeachingTaskService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
@WebServlet("/TeachingTaskServlet")
public class TeachingTaskServlet extends BaseServlet {

    private ITeachingTaskService teachingTaskService = null;

    /**
     * 获取所有的教学任务
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeachingTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildErrorParameter();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<TeachingTask> ttModel = null;
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (keyWord == null || keyWord.isEmpty()) {
            keyWord = "";
        }
        ttModel = teachingTaskService.getTeachingTasks(keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        JSONObject object = (JSONObject) JSON.toJSON(ttModel);
        return object.toJSONString();
    }

    /**
     * 获取当前教师的教学计划
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeaTeachingTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildErrorParameter();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<TeachingTask> ttModel = null;
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (keyWord == null || keyWord.isEmpty()) {
            keyWord = "";
        }
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("obj");
        ttModel = teachingTaskService.getTeaTeachingTasks(teacher.getId(), keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        JSONObject object = (JSONObject) JSON.toJSON(ttModel);
        return object.toJSONString();
    }

    /**
     * 删除教学计划
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteTeachingTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        if (teachingTaskNum == null || teachingTaskNum.isEmpty()) {
            return ResponseModel.buildErrorParameter();
        }
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (teachingTaskService.deleteTeachingTask(teachingTaskNum)) {
            return ResponseModel.buildMessage(true, "删除成功");
        } else {
            return ResponseModel.buildMessage(false, "删除失败");
        }
    }

    /**
     * 除了此学生的教学计划数据
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeachingTaskExceptStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildErrorParameter();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<TeachingTask> ttModel = null;
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        if (keyWord == null || keyWord.isEmpty()) {
            keyWord = "";
        }
        ttModel = teachingTaskService.getAllTeachingTaskExceptThis(stu.getId(), keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        JSONObject object = (JSONObject) JSON.toJSON(ttModel);
        return object.toJSONString();
    }

    /**
     * 获取已选课程
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeachingTaskSelf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildErrorParameter();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<TeachingTask> ttModel = null;
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        if (keyWord == null || keyWord.isEmpty()) {
            keyWord = "";
        }
        ttModel = teachingTaskService.getTeachingTaskSelf(stu.getId(), keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        JSONObject object = (JSONObject) JSON.toJSON(ttModel);
        return object.toJSONString();
    }

    /**
     * 插入教学计划
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String insertTeachingTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        String courseName = request.getParameter("courseName");
        String teacherId = request.getParameter("teacherId");
        String location = request.getParameter("location");
        String startTime = request.getParameter("startTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(startTime);
        Timestamp timestamp = new Timestamp(date.getTime());
        TeachingTask teachingTask = new TeachingTask(teachingTaskNum, courseName, teacherId, location, timestamp);
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (teachingTaskService.insertTeachingTask(teachingTask)) {
            return ResponseModel.buildMessage(true, "插入成功");
        } else {
            return ResponseModel.buildMessage(false, "插入失败");
        }
    }

    /**
     * 更新教学任务
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateTeachingTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        String courseName = request.getParameter("courseName");
        String teacherId = request.getParameter("teacherId");
        String location = request.getParameter("location");
        String startTime = request.getParameter("startTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(startTime);
        Timestamp timestamp = new Timestamp(date.getTime());
        TeachingTask teachingTask = new TeachingTask(teachingTaskNum, courseName, teacherId, location, timestamp);
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (teachingTaskService.updateTeachingTask(teachingTask)) {
            return ResponseModel.buildMessage(true, "更新成功");
        } else {
            return ResponseModel.buildMessage(false, "更新失败");
        }
    }
}
