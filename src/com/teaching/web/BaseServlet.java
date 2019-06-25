package com.teaching.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        String action = request.getParameter("action");
        if (action == null) {
            throw new RuntimeException("参数异常");
        }
        System.out.println("action=" + action);
        Class clazz = this.getClass();
        Method method = null;
        PrintWriter writer = null;
        try {
            method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            String invoke = (String) method.invoke(this, request, response);
            if (invoke != null && !invoke.isEmpty()) {
                writer = response.getWriter();
                writer.println(invoke);
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
