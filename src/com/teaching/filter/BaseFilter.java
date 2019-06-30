package com.teaching.filter;

import com.teaching.domain.Admin;
import com.teaching.domain.Student;
import com.teaching.domain.Teacher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
@WebFilter(filterName = "BaseFilter")
public class BaseFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        System.out.println("BaseFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //设置统一编码格式
        request.setCharacterEncoding("utf-8");
        //无需登录过滤就能浏览的界面，像登录界面、注册界面、还有一些保存图片的路径，把它们单抽取出来
        if (request.getServletPath().endsWith("login.jsp")
                || request.getServletPath().endsWith(".png")
                || "login".equals(request.getParameter("action"))) {
            chain.doFilter(req, resp);
        } else {
            //获取登录过后，全局共享数据
            Object obj = request.getSession().getAttribute("obj");
            if (obj != null) {
                chain.doFilter(req, resp);
//                if((obj instanceof Student) && filterStu(request)){//如果此对象为学生
//                    chain.doFilter(req, resp);
//                }else if (obj instanceof Teacher){//如果此对象为教师
//                    chain.doFilter(req, resp);
//                }else if(obj instanceof Admin){//如果此对象为管理员
//                    chain.doFilter(req, resp);
//                }else{
//                    response.sendRedirect(request.getContextPath() + "/login.jsp");//重定向到登录界面
//                }
            } else {
                //如果没有取到用户信息,非法访问，没有登陆，跳转到登陆页面
                response.sendRedirect(request.getContextPath() + "/login.jsp");//重定向到登录界面
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    /**
     * 过滤学生请求
     * @param request
     * @return
     */
    public boolean filterStu(HttpServletRequest request){
        String url = request.getRequestURL().toString();
        String action = request.getParameter("action");
        if(("getTeachingTaskSelf").equals(action)
                ||("retireCourse").equals(action)
                ||("getTeachingTaskExceptStu").equals(action)
                ||("selectCourse").equals(action)
                ||("exit").equals(action)
                ||("updateStudent").equals(action)
                ||("updateStuPassword").equals(action)
                ||url.endsWith("/student/student.jsp")){
            return true;
        }
        return false;
    }

}
