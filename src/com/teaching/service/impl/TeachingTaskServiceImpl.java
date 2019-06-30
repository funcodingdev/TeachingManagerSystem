package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.ITeachingTaskDao;
import com.teaching.domain.ResponseModel;
import com.teaching.domain.TeachingTask;
import com.teaching.service.ITeachingTaskService;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class TeachingTaskServiceImpl implements ITeachingTaskService {

    private ITeachingTaskDao teachingTaskDao = null;

    @Override
    public ResponseModel<TeachingTask> getAllTeachingTask() {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        List<TeachingTask> allStudent = teachingTaskDao.listAllTeachingTasks();
        ResponseModel<TeachingTask> model = ResponseModel.buildModelSuccess(allStudent.size(), allStudent);
        return model;
    }

    @Override
    public ResponseModel<TeachingTask> getTeachingTasks(String keyWord) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        List<TeachingTask> teachingTasks = teachingTaskDao.listTeachingTasks(keyWord);
        ResponseModel<TeachingTask> model = ResponseModel.buildModelSuccess(teachingTasks.size(), teachingTasks);
        return model;
    }

    @Override
    public ResponseModel<TeachingTask> getTeachingTasks(int currentPage, int perPageSize) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int totalNum = teachingTaskDao.getTTCount();
        ResponseModel<TeachingTask> ttModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<TeachingTask> teachingTasks = teachingTaskDao.listTeachingTasks(ttModel.getPageStart(), ttModel.getPageEnd());
        ttModel.setData(teachingTasks);
        return ttModel;
    }

    @Override
    public ResponseModel<TeachingTask> getTeachingTasks(String keyWord, int currentPage, int perPageSize) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int totalNum = teachingTaskDao.getTTCount(keyWord);
        ResponseModel<TeachingTask> ttModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<TeachingTask> teachingTasks = teachingTaskDao.listTeachingTasks(keyWord, ttModel.getPageStart(), ttModel.getPageEnd());
        ttModel.setData(teachingTasks);
        return ttModel;
    }

    @Override
    public ResponseModel<TeachingTask> getAllTeachingTaskExceptThis(String stuId, String keyWord, int currentPage, int perPageSize) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int totalNum = teachingTaskDao.getTTCount(keyWord);
        ResponseModel<TeachingTask> ttModel = new ResponseModel<>(totalNum, currentPage, perPageSize);
        List<TeachingTask> teachingTasks = teachingTaskDao.listAllTeachingTaskExceptThis(stuId, keyWord, ttModel.getPageStart(), ttModel.getPageEnd());
        ttModel.setData(teachingTasks);
        return ttModel;
    }

    @Override
    public ResponseModel<TeachingTask> getTeachingTaskSelf(String stuId, String keyWord, int currentPage, int perPageSize) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int totalNum = teachingTaskDao.getTTCount(keyWord);
        ResponseModel<TeachingTask> ttModel = new ResponseModel<>(totalNum,currentPage,perPageSize);
        List<TeachingTask> teachingTasks = teachingTaskDao.listAllTeachingTaskToSelf(stuId,keyWord,ttModel.getPageStart(), ttModel.getPageEnd());
        ttModel.setData(teachingTasks);
        return ttModel;
    }

    @Override
    public ResponseModel<TeachingTask> getTeaTeachingTasks(String teaId, String keyWord, int currentPage, int perPageSize) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int totalNum = teachingTaskDao.getTTCountByTeaId(teaId);
        ResponseModel<TeachingTask> ttModel = new ResponseModel<>(totalNum,currentPage,perPageSize);
        List<TeachingTask> teachingTasks = teachingTaskDao.listAllTeachingTasksToTea(teaId,keyWord,ttModel.getPageStart(), ttModel.getPageEnd());
        ttModel.setData(teachingTasks);
        return ttModel;
    }

    @Override
    public boolean deleteTeachingTask(String teachingTask) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int result = teachingTaskDao.deleteTeachingTask(teachingTask);
        return result > 0;
    }

    @Override
    public boolean insertTeachingTask(TeachingTask teachingTask) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int result = teachingTaskDao.insertTeachingTask(teachingTask);
        return result > 0;
    }

    @Override
    public boolean updateTeachingTask(TeachingTask teachingTask) {
        teachingTaskDao = DaoFactory.getTeachingTaskDao();
        int result = teachingTaskDao.updateTeachingTask(teachingTask);
        return result > 0;
    }

}
