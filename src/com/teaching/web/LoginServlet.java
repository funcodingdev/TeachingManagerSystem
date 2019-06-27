package com.teaching.web;

import com.teaching.domain.Admin;
import com.teaching.domain.Student;
import com.teaching.domain.Teacher;
import com.teaching.service.IAdminService;
import com.teaching.service.IStudentService;
import com.teaching.service.ITeacherService;
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
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
    /**
     * 登陆
     *
     * @param request
     * @param response
     * @return
     */
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("id");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        System.out.println(username + "," + password + "," + role);
        if(username == null || password == null || role == null){
            return null;
        }
        String invoke = null;
        String basePath = request.getContextPath();
        Object loginObj = null;//登陆对象
        HttpSession session = request.getSession();
        switch (role) {
            case "0": {//学生
                IStudentService studentService = ServiceFactory.getStudentService();
                Student student = studentService.findStudent(username, password);
                if (student != null) {//登陆成功
                    invoke = basePath + "/student/student.jsp";
                    loginObj = student;
                    session.setAttribute("role", "学生");
                }
                break;
            }
            case "1": {//教师
                ITeacherService teacherService = ServiceFactory.getTeacherService();
                Teacher teacher = teacherService.findTeacher(username, password);
                if (teacher != null) {
                    invoke = basePath + "/teacher/teacher.jsp";
                    loginObj = teacher;
                    session.setAttribute("role", "教师");
                }
                break;
            }
            case "2": {//管理员
                IAdminService adminService = ServiceFactory.getAdminService();
                Admin admin = adminService.findAdmin(username, password);
                if (admin != null) {
                    invoke = basePath + "/admin/admin.jsp";
                    loginObj = admin;
                    session.setAttribute("role", "管理员");
                }
                break;
            }
        }
        try {
            if (invoke == null) {//验证失败
                request.setAttribute("error", "用户名或密码错误！");
                request.setAttribute("id", username);
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            } else {//验证成功
                session.setAttribute("obj", loginObj);
                response.sendRedirect(invoke);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
