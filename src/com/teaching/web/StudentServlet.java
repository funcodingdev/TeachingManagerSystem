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
            keyWord = "";
        }
        stuModel = studentService.getStudents(keyWord);
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
            keyWord = "";
        }
        stuModel = studentService.getStudents(keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        JSONObject object = (JSONObject) JSON.toJSON(stuModel);
        return object.toJSONString();
    }

    /**
     * 获取选课学生
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getSCStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        studentService = ServiceFactory.getStudentService();
        ResponseModel<Student> stuModel = studentService.getSCStudent(teachingTaskNum);
        return JSON.toJSONString(stuModel);
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
        //获取表单学生信息
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String dept = request.getParameter("department");
        String sClass = request.getParameter("sClass");
        Department department = ServiceFactory.getDeptService().getDeptName(dept);
        if (department == null) {
            return "[false]";
        }
        Student stu = new Student(id,name,sex,Integer.valueOf(age),dept,sClass,id);
        //设置部门名
        stu.setDepartment(department.getName());
        studentService = ServiceFactory.getStudentService();
        JSONArray array = new JSONArray();
        array.add(studentService.insertStudent(stu));
        return array.toJSONString();
    }

}
