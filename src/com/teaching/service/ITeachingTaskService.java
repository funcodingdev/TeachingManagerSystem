package com.teaching.service;

import com.teaching.domain.ResponseModel;
import com.teaching.domain.TeachingTask;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ITeachingTaskService {
    ResponseModel<TeachingTask> getAllTeachingTask();//获取所有教学任务

    ResponseModel<TeachingTask> getTeachingTasks(String keyWord);//模糊查询

    ResponseModel<TeachingTask> getTeachingTasks(int pageStart, int pageEnd);//获取当前范围的教学任务

    ResponseModel<TeachingTask> getTeachingTasks(String keyWord, int currentPage, int perPageSize);//获取模糊查询当前范围的教学任务

    boolean deleteTeachingTask(String teachingTask);//删除教学任务

    boolean insertTeachingTask(TeachingTask teachingTask);//插入教学任务

    boolean updateTeachingTask(TeachingTask teachingTask);//更新教学任务
}
