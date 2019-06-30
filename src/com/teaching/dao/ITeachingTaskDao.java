package com.teaching.dao;

import com.teaching.domain.ResponseModel;
import com.teaching.domain.TeachingTask;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ITeachingTaskDao {
    List<TeachingTask> listAllTeachingTasks();//获取所有教学任务

    List<TeachingTask> listTeachingTasks(String keyWord);//模糊查询教学任务

    List<TeachingTask> listTeachingTasks(int pageStart, int pageEnd);//获取当前范围的教学任务

    List<TeachingTask> listTeachingTasks(String keyWord, Integer pageStart, Integer pageEnd);//获取模糊查询当前范围的教学任务

    List<TeachingTask> listAllTeachingTaskExceptThis(String stuId, String keyWord, Integer pageStart, Integer pageEnd);//获取除了此学生的所有选课信息

    List<TeachingTask> listAllTeachingTaskToSelf(String stuId, String keyWord, Integer pageStart, Integer pageEnd);//学生获取选课信息

    List<TeachingTask> listAllTeachingTasksToTea(String teaId, String keyWord, Integer pageStart, Integer pageEnd);//教师获取教学任务

    int deleteTeachingTask(String id);//删除教学任务

    int insertTeachingTask(TeachingTask teachingTask);//插入教学任务

    int updateTeachingTask(TeachingTask teachingTask);//更新教学任务

    int getTTCount(String... args);//获取所有教学任务数量

    int getTTCountByTeaId(String teaId);//获取当前教师的所有教学任务

//    int getTTCountExceptThis(String stuId);//获取除了此学生所选了的所有教学任务数量
}
