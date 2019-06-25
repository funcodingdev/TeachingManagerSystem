package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.Department;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Student;
import com.teaching.service.IStudentService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends BaseServlet {
    IStudentService studentService = null;

    /**
     * 获取所有学生
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        studentService = ServiceFactory.getStudentService();
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Student> stuModel = null;
        if (keyWord == null || keyWord.isEmpty()) {
            stuModel = studentService.getAllStudent();
        } else {
            stuModel = studentService.getStudents(keyWord);
        }
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

    /**
     * 获取分页学生数据
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildError().toString();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<Student> stuModel = null;
        studentService = ServiceFactory.getStudentService();
        if (keyWord == null || keyWord.isEmpty()) {
            stuModel = studentService.getStudents(Integer.valueOf(page), Integer.valueOf(limit));
        } else {
            stuModel = studentService.getStudents(keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        }
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

    /**
     * 删除学生
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            return null;
        }
        studentService = ServiceFactory.getStudentService();
        if (studentService.deleteStudent(id)) {
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        }
        return null;
    }

    /**
     * 插入学生
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("formdata");
        //获取表单学生信息
        Student stu = JSON.parseObject(data, Student.class);
        Department department = ServiceFactory.getDeptService().getDeptName(stu.getDepartment());
        if (department == null) {
            return null;
        }
        //设置部门名
        stu.setDepartment(department.getName());
        //设置密码
        stu.setPassword(stu.getId());
        studentService = ServiceFactory.getStudentService();
        if (studentService.insertStudent(stu)) {
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        } else {
            return null;
        }
    }

}
