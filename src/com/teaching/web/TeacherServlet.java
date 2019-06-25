package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.Department;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Teacher;
import com.teaching.domain.Teacher;
import com.teaching.service.ITeacherService;
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
@WebServlet("/TeacherServlet")
public class TeacherServlet extends BaseServlet {
    private ITeacherService teacherService;

    /**
     * 获取所有的教师
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getAllTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        teacherService = ServiceFactory.getTeacherService();
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Teacher> stuModel = null;
        if(keyWord == null || keyWord.isEmpty()){
            stuModel = teacherService.getAllTeacher();
        }else{
            stuModel = teacherService.getTeachers(keyWord);
        }
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

    /**
     * 获取分页教师数据
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeachers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildError().toString();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Teacher> teaModel = null;
        teacherService = ServiceFactory.getTeacherService();
        if (keyWord == null || keyWord.isEmpty()) {
            teaModel = teacherService.getTeachers(Integer.valueOf(page), Integer.valueOf(limit));
        } else {
            teaModel = teacherService.getTeachers(keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        }
        JSONObject object = (JSONObject) JSON.toJSON(teaModel);
        return object.toJSONString();
    }
    
    /**
     * 删除教师
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            return null;
        }
        teacherService = ServiceFactory.getTeacherService();
        if (teacherService.deleteTeacher(id)) {
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        }
        return null;
    }

    /**
     * 插入教师
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String insertTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("formdata");
        //获取表单教师信息
        Teacher teacher = JSON.parseObject(data, Teacher.class);
        //设置密码
        teacher.setPassword(teacher.getId());
        teacherService = ServiceFactory.getTeacherService();
        if (teacherService.insertTeacher(teacher)) {
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        } else {
            return null;
        }
    }

}
