package com.teaching.web;

import com.alibaba.fastjson.JSONArray;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.SClass;
import com.teaching.service.ISClassService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
@WebServlet("/SClassServlet")
public class SClassServlet extends BaseServlet {

    private ISClassService classService = null;

    /**
     * 获取所有的班级
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public String getAllClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String department = request.getParameter("department");
        if(department == null){
            return ResponseModel.buildErrorParameter();
        }
        classService = ServiceFactory.getSClassService();
        List<SClass> allClass = classService.listAllSClass(department);
        JSONArray array = new JSONArray();
        for (SClass aClass : allClass) {
            array.add(aClass.getName());
        }
        return array.toJSONString();
    }
}
