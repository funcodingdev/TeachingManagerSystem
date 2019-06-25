package com.teaching.dao.impl;

import com.teaching.dao.ICourseDao;
import com.teaching.domain.Course;
import com.teaching.jdbc.handler.BeanListHandler;
import com.teaching.jdbc.util.CRUDTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class CourseImpl implements ICourseDao {

    @Override
    public List<Course> getAllCourse() {
        String sql = "select * from course";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Course.class));
    }

    @Override
    public List<Course> getCourses(String keyWord) {
        String sql = "select * from course where id like '%" + keyWord + "%'";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Course.class));
    }

    @Override
    public List<Course> getCourses(int start, int end) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,c.* FROM (SELECT * FROM course ORDER BY id ASC) c WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Course.class), end, start);
    }

    @Override
    public List<Course> getCourses(String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,c.* FROM (SELECT * FROM course where id like '%" + keyWord + "%' ORDER BY id ASC) c WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Course.class), pageEnd, pageStart);
    }

    @Override
    public int deleteCourse(String id) {
        String sql = "delete from course where id = ?";
        return CRUDTemplate.executeUpdate(sql, id);
    }

    @Override
    public int insertCourse(Course course) {
        String sql = "insert into Course(id,name,credit,period) values(?,?,?,?)";
        return CRUDTemplate.executeUpdate(sql, course.getId(), course.getName(), course.getCredit(), course.getPeriod());
    }

    @Override
    public int updateCourse(Course course) {
        String sql = "update Course set name = ?,credit = ?,period=? where id = ?";
        return CRUDTemplate.executeUpdate(sql, course.getName(), course.getCredit(), course.getPeriod(), course.getId());
    }

    @Override
    public int getCourseCount(String... args) {
        String sql = "select count(*) total from Course where id like ?";
        BigDecimal decimal = null;
        if (args.length > 0) {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%" + args[0] + "%");
        } else {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%%");
        }
        return decimal.intValue();
    }
}
