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
    public List<TeachingTask> listAllTeachingTasks() {
        String sql = "select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class));
    }

    @Override
    public List<TeachingTask> listTeachingTasks(String keyWord) {
        String sql = "select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where courseName like '%" + keyWord + "%'";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class));
    }

    @Override
    public List<TeachingTask> listTeachingTasks(int pageStart, int pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tt.* FROM (select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name ORDER BY teachingTaskNum ASC) tt WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), pageEnd, pageStart);
    }

    @Override
    public List<TeachingTask> listTeachingTasks(String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tt.* FROM (select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where courseName like '%" + keyWord + "%' ORDER BY teachingTaskNum ASC) tt WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), pageEnd, pageStart);
    }

    @Override
    public List<TeachingTask> listAllTeachingTaskExceptThis(String stuId, String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "select * from (SELECT * FROM(SELECT ROWNUM NO,tt.* FROM (select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where courseName like '%" + keyWord + "%' ORDER BY teachingTaskNum ASC) tt WHERE ROWNUM<=?) WHERE NO >=?) where teachingTaskNum not in (select teachingTaskNum from selectCourse where stuId = ?)";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), pageEnd, pageStart, stuId);
    }

    @Override
    public List<TeachingTask> listAllTeachingTaskToSelf(String stuId, String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "select * from (SELECT * FROM(SELECT ROWNUM NO,stu.* FROM (select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where teachingTaskNum like '%" + keyWord + "%' ORDER BY teachingTaskNum ASC) stu WHERE ROWNUM<=?) WHERE NO >=?) s inner join selectCourse on s.teachingTaskNum = selectCourse.teachingTaskNum where stuId = ?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), pageEnd, pageStart, stuId);
    }

    @Override
    public List<TeachingTask> listAllTeachingTasksToTea(String teaId, String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tt.* FROM (select teachingTaskNum,totalNum,location,startTime,Course.id as courseId,Course.name as courseName,Teacher.id as teacherId,Teacher.name as teacherName from TeachingTask inner join Teacher on TeachingTask.teacherId = Teacher.id inner join Course on TeachingTask.courseName = Course.name where courseName like '%" + keyWord + "%' and TeachingTask.teacherId = ? ORDER BY teachingTaskNum ASC) tt WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(TeachingTask.class), teaId, pageEnd, pageStart);
    }

    @Override
    public int deleteTeachingTask(String teachingTaskNum) {
        String sql = "delete from teachingTask where teachingTaskNum = ?";
        return CRUDTemplate.executeUpdate(sql, teachingTaskNum);
    }

    @Override
    public int insertTeachingTask(TeachingTask teachingTask) {
        String sql = "insert into teachingTask(teachingTaskNum,courseName,teacherId,location,startTime) values(?,?,?,?,?)";
        return CRUDTemplate.executeUpdate(sql, teachingTask.getTeachingTaskNum(), teachingTask.getCourseName(), teachingTask.getTeacherId(), teachingTask.getLocation(), teachingTask.getStartTime());
    }

    @Override
    public int updateTeachingTask(TeachingTask teachingTask) {
        String sql = "update teachingTask set teacherId = ?,location = ?,startTime = ? where teachingTaskNum = ?";
        return CRUDTemplate.executeUpdate(sql, teachingTask.getTeacherId(), teachingTask.getLocation(), teachingTask.getStartTime(), teachingTask.getTeachingTaskNum());
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

    @Override
    public int getTTCountByTeaId(String teaId) {
        String sql = "select count(*) total from teachingTask where teacherId = ?";
        BigDecimal decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", teaId);
        return decimal.intValue();
    }

}
