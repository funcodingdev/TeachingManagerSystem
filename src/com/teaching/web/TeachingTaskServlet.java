package com.teaching.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.Student;
import com.teaching.domain.TeachingTask;
import com.teaching.service.ITeachingTaskService;
import com.teaching.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
@WebServlet("/TeachingTaskServlet")
public class TeachingTaskServlet extends BaseServlet {

    private ITeachingTaskService teachingTaskService = null;

    /**
     * 获取所有的教学任务
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String getTeachingTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        if (page == null || limit == null) {
            return ResponseModel.buildError().toString();
        }
        String keyWord = request.getParameter("keyWord");
        ResponseModel<TeachingTask> ttModel = null;
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (keyWord == null || keyWord.isEmpty()) {
            ttModel = teachingTaskService.getTeachingTasks(Integer.valueOf(page), Integer.valueOf(limit));
        } else {
            ttModel = teachingTaskService.getTeachingTasks(keyWord, Integer.valueOf(page), Integer.valueOf(limit));
        }
        JSONObject object = (JSONObject) JSON.toJSON(ttModel);
        return object.toJSONString();
    }

    /**
     * 删除教学计划
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteTeachingTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            return null;
        }
        teachingTaskService = ServiceFactory.getTeachingTaskService();
        if (teachingTaskService.deleteTeachingTask(id)) {
            JSONArray array = new JSONArray();
            array.add(true);
            return array.toJSONString();
        }
        return null;
    }
}