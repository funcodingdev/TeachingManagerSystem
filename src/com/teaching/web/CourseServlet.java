package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.Course;
import com.teaching.domain.ResponseModel;
import com.teaching.service.ICourseService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends BaseServlet {
    private ICourseService courseService = null;
    /**
     * 获取所有课程
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String getAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        courseService = ServiceFactory.getCourseService();
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Course> stuModel = null;
        if(keyWord == null || keyWord.isEmpty()){
            stuModel = courseService.getAllCourse();
        }else{
            stuModel = courseService.getCourses(keyWord);
        }
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
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
            return ResponseModel.buildError().toString();
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
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        }
        return null;
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
        String data = request.getParameter("formdata");
        //获取表单课程信息
        Course course = JSON.parseObject(data, Course.class);
        courseService = ServiceFactory.getCourseService();
        if (courseService.insertCourse(course)) {
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        } else {
            return null;
        }
    }


}
