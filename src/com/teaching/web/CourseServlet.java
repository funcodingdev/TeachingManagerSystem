package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.Course;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Teacher;
import com.teaching.service.ICourseService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends BaseServlet {
    private ICourseService courseService = null;

    /**
     * 获取所有课程
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String getAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        courseService = ServiceFactory.getCourseService();
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Course> stuModel = null;
        if (keyWord == null || keyWord.isEmpty()) {
            stuModel = courseService.getAllCourses();
        } else {
            stuModel = courseService.getCourses(keyWord);
        }
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

    /**
     * 获取所有课程名称
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getAllCourseName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        courseService = ServiceFactory.getCourseService();
        ResponseModel<Course> allCourse = courseService.getAllCourses();
        List<Course> courses = allCourse.getData();
        JSONArray array = new JSONArray();
        for (Course course : courses) {
            array.add(course.getName());
        }
        return array.toJSONString();
    }


    /**
     * 获取分页课程数据
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getCourses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildModelError().toString();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Course> stuModel = null;
        courseService = ServiceFactory.getCourseService();
        if (keyWord == null || keyWord.isEmpty()) {
            stuModel = courseService.getCourses(Integer.valueOf(page), Integer.valueOf(limit));
        } else {
            stuModel = courseService.getCourses(keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        }
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

    /**
     * 删除课程
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            return null;
        }
        courseService = ServiceFactory.getCourseService();
        if (courseService.deleteCourse(id)) {
            return ResponseModel.buildMessage(true, "删除成功");
        } else {
            return ResponseModel.buildMessage(false, "删除失败");
        }
    }

    /**
     * 插入课程
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String insertCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单课程信息
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String credit = request.getParameter("credit");
        String period = request.getParameter("period");
        Course course = new Course(id, name, Integer.valueOf(credit), Integer.valueOf(period));
        courseService = ServiceFactory.getCourseService();
        if (courseService.insertCourse(course)) {
            return ResponseModel.buildMessage(true, "插入成功");
        } else {
            return ResponseModel.buildMessage(false, "插入失败");
        }
    }

    /**
     * 修改课程
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单课程信息
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String credit = request.getParameter("credit");
        String period = request.getParameter("period");
        Course course = new Course(id, name, Integer.valueOf(credit), Integer.valueOf(period));
        courseService = ServiceFactory.getCourseService();
        if (courseService.updateCourse(course)) {
            return ResponseModel.buildMessage(true, "更新成功");
        } else {
            return ResponseModel.buildMessage(false, "更新失败");
        }
    }

    /**
     * 获取当前教师的课程
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeaCourses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildModelError().toString();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Course> stuModel = null;
        courseService = ServiceFactory.getCourseService();
        if (keyWord == null || keyWord.isEmpty()) {
            keyWord = "";
        }
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return null;
        }
        Teacher teacher = (Teacher) session.getAttribute("obj");
        stuModel = courseService.getCoursesToTea(teacher.getId(), keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

}
