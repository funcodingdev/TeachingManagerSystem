package com.teaching.dao.impl;

import com.teaching.dao.ITeachingTaskDao;
import com.teaching.domain.TeachingTask;
import com.teaching.jdbc.handler.BeanListHandler;
import com.teaching.jdbc.util.CRUDTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class TeachingTaskImpl implements ITeachingTaskDao {
    @Override
    public List<TeachingTask> getAllTeachingTask() {
        String sql = "select teachingTaskNum,totalNum,location,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class));
    }

    @Override
    public List<TeachingTask> getTeachingTasks(String keyWord) {
        String sql = "select teachingTaskNum,totalNum,location,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where teachingTaskNum like '%" + keyWord + "%'";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class));
    }

    @Override
    public List<TeachingTask> getTeachingTasks(int pageStart, int pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tt.* FROM (select teachingTaskNum,totalNum,location,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name ORDER BY teachingTaskNum ASC) tt WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), pageEnd, pageStart);
    }

    @Override
    public List<TeachingTask> getTeachingTasks(String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tt.* FROM (select teachingTaskNum,totalNum,location,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where teachingTaskNum like '%" + keyWord + "%' ORDER BY teachingTaskNum ASC) tt WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), pageEnd, pageStart);
    }

    @Override
    public int deleteTeachingTask(String teachingTaskNum) {
        String sql = "delete from teachingTask where teachingTaskNum = ?";
        return CRUDTemplate.executeUpdate(sql, teachingTaskNum);
    }

    @Override
    public int insertTeachingTask(TeachingTask teachingTask) {
        String sql = "insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values(?,?,?,?)";
        return CRUDTemplate.executeUpdate(sql, teachingTask.getTeachingTaskNum(), teachingTask.getCourseName(), teachingTask.getTeacherId(), teachingTask.getLocation());
    }

    @Override
    public int updateTeachingTask(TeachingTask teachingTask) {
        String sql = "update teachingTask set courseName = ?,teacherId = ?,location = ? where teachingTaskNum = ?";
        return CRUDTemplate.executeUpdate(sql, teachingTask.getCourseName(), teachingTask.getTeacherId(), teachingTask.getLocation(), teachingTask.getTeachingTaskNum());
    }

    @Override
    public int getTTCount(String... args) {
        String sql = "select count(*) total from teachingTask where teachingTaskNum like ?";
        BigDecimal decimal = null;
        if (args.length > 0) {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%" + args[0] + "%");
        } else {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%%");
        }
        return decimal.intValue();
    }
}
