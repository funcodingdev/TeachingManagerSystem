package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.*;
import com.teaching.service.IStudentService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            return ResponseModel.buildErrorParameter();
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
     * 获取选课学生成绩
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getSCGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teachingTaskNum = request.getParameter("teachingTaskNum");
        studentService = ServiceFactory.getStudentService();
        ResponseModel<Grade> stuModel = studentService.getSCGrade(teachingTaskNum);
        return JSON.toJSONString(stuModel);
    }

    /**
     * 修改学生成绩
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateSCGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = request.getParameter("json");
        JSONObject object = JSONObject.parseObject(json);
        Grade gradeModel = new Grade(object.getString("stuId"), object.getString("teachingTaskNum"), object.getIntValue("grade"));
        studentService = ServiceFactory.getStudentService();
        if (studentService.updateSCGrade(gradeModel)) {
            return ResponseModel.buildMessage(true, "更新成功");
        } else {
            return ResponseModel.buildMessage(false, "更新失败");
        }
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
            return ResponseModel.buildErrorParameter();
        }
        studentService = ServiceFactory.getStudentService();
        if (studentService.deleteStudent(id)) {
            return ResponseModel.buildMessage(true, "删除成功");
        } else {
            return ResponseModel.buildMessage(false, "删除成功");
        }
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
        Department department = ServiceFactory.getDeptService().getDepartment(dept);
        if (department == null) {
            return ResponseModel.buildErrorParameter();
        }
        Student stu = new Student(id, name, sex, Integer.valueOf(age), dept, sClass, id);
        //设置部门名
        stu.setDepartment(department.getName());
        studentService = ServiceFactory.getStudentService();
        if (studentService.insertStudent(stu)) {
            return ResponseModel.buildMessage(true, "插入成功");
        } else {
            return ResponseModel.buildMessage(false, "插入失败");
        }
    }

    /**
     * 更新学生基本信息
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单学生信息
        String id = request.getParameter("id");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        Student stu = new Student(id, Integer.valueOf(age), phone);
        studentService = ServiceFactory.getStudentService();
        if (studentService.updateStudent(stu)) {
            HttpSession session = request.getSession();
            Student newStu = (Student) session.getAttribute("obj");
            newStu.setAge(Integer.valueOf(age));
            newStu.setPhone(phone);
            session.setAttribute("obj", newStu);
            return ResponseModel.buildMessage(true, "更新成功");
        } else {
            return ResponseModel.buildMessage(false, "更新失败");
        }
    }

    /**
     * 更新学生密码
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateStuPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        if (oldPassword == null || newPassword == null) {
            return ResponseModel.buildErrorParameter();
        }
        HttpSession session = request.getSession();
        Student stu = (Student) session.getAttribute("obj");
        studentService = ServiceFactory.getStudentService();
        if (studentService.updateStuPassword(stu.getId(), oldPassword, newPassword)) {
            return ResponseModel.buildMessage(true, "密码修改成功");
        } else {
            return ResponseModel.buildMessage(false, "密码修改失败");
        }
    }
}
