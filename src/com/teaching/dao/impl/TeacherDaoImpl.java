package com.teaching.dao.impl;

import com.teaching.dao.ITeacherDao;
import com.teaching.domain.Teacher;
import com.teaching.jdbc.handler.BeanHandler;
import com.teaching.jdbc.handler.BeanListHandler;
import com.teaching.jdbc.util.CRUDTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class TeacherDaoImpl implements ITeacherDao {
    @Override
    public List<Teacher> getAllTeacher() {
        String sql = "select * from teacher";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Teacher.class));
    }

    @Override
    public List<Teacher> getTeachers(String keyWord) {
        String sql = "select * from teacher where id like '%" + keyWord + "%'";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Teacher.class));
    }

    @Override
    public List<Teacher> getTeachers(int start, int end) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tea.* FROM (SELECT * FROM teacher ORDER BY id ASC) tea WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Teacher.class), end, start);
    }

    @Override
    public List<Teacher> getTeachers(String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,tea.* FROM (SELECT * FROM teacher where id like '%" + keyWord + "%' ORDER BY id ASC) tea WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Teacher.class), pageEnd, pageStart);
    }

    @Override
    public int deleteTeacher(String id) {
        String sql = "delete from teacher where id = ?";
        return CRUDTemplate.executeUpdate(sql, id);
    }

    @Override
    public int insertTeacher(Teacher tea) {
        String sql = "insert into teacher(id,name,sex,age,identity,password) values(?,?,?,?,?,?)";
        return CRUDTemplate.executeUpdate(sql, tea.getId(), tea.getName(), tea.getSex(),
                tea.getAge(), tea.getIdentity(), tea.getPassword());
    }

    @Override
    public int updateTeacher(Teacher tea) {
        String sql = "update teacher set name = ?,sex = ?,age = ?,identity = ?,password = ?,image = ?) where id = ?";
        return CRUDTemplate.executeUpdate(sql, tea.getName(), tea.getSex(),
                tea.getAge(), tea.getIdentity(), tea.getPassword(), tea.getImage(), tea.getId());
    }

    @Override
    public Teacher findTeacher(String id, String password) {
        String sql = "select * from teacher where id = ? and password = ?";
        return CRUDTemplate.executeQuery(sql, new BeanHandler<>(Teacher.class), id, password);
    }

    @Override
    public int getTeaCount(String... args) {
        String sql = "select count(*) total from teacher where id like ?";
        BigDecimal decimal = null;
        if (args.length > 0) {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%" + args[0] + "%");
        } else {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%%");
        }
        return decimal.intValue();
    }
}
