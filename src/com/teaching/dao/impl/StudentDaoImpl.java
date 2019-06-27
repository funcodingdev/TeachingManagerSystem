package com.teaching.dao.impl;

import com.teaching.dao.IStudentDao;
import com.teaching.domain.Student;
import com.teaching.jdbc.handler.BeanHandler;
import com.teaching.jdbc.handler.BeanListHandler;
import com.teaching.jdbc.util.CRUDTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class StudentDaoImpl implements IStudentDao {
    @Override
    public List<Student> getAllStudent() {
        String sql = "select * from student";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class));
    }

    @Override
    public List<Student> getStudents(String keyWord) {
        String sql = "select * from student where id like '%" + keyWord + "%'";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class));
    }

    @Override
    public List<Student> getStudents(int start, int end) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,stu.* FROM (SELECT * FROM student ORDER BY id ASC) stu WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class), end, start);
    }

    @Override
    public List<Student> getStudents(String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,stu.* FROM (SELECT * FROM student where id like '%" + keyWord + "%' ORDER BY id ASC) stu WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class), pageEnd, pageStart);
    }

    @Override
    public List<Student> getSCStudent(String teachingTaskNum) {
        String sql = "select Student.*,selectCourse.grade from selectCourse inner join Student on selectCourse.stuId = Student.id where teachingTaskNum = ?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class), teachingTaskNum);
    }

    @Override
    public int deleteStudent(String id) {
        String sql = "delete from student where id = ?";
        return CRUDTemplate.executeUpdate(sql, id);
    }

    @Override
    public int insertStudent(Student stu) {
        String sql = "insert into student(id,name,sex,age,department,sclass,password) values(?,?,?,?,?,?,?)";
        return CRUDTemplate.executeUpdate(sql, stu.getId(), stu.getName(), stu.getSex(),
                stu.getAge(), stu.getDepartment(), stu.getSclass(), stu.getPassword());
    }

    @Override
    public int updateStudent(Student stu) {
        String sql = "update student set name = ?,sex = ?,age = ?,department = ?,sclass = ?,password = ?,phone = ?) where id = ?";
        return CRUDTemplate.executeUpdate(sql, stu.getName(), stu.getSex(),
                stu.getAge(), stu.getDepartment(), stu.getSclass(), stu.getPassword(), stu.getPhone(), stu.getId());
    }

    @Override
    public Student findStudent(String id, String password) {
        String sql = "select * from student where id = ? and password = ?";
        return CRUDTemplate.executeQuery(sql, new BeanHandler<>(Student.class), id, password);
    }

    @Override
    public int getStuCount(String... args) {
        String sql = "select count(*) total from student where id like ?";
        BigDecimal decimal = null;
        if (args.length > 0) {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%" + args[0] + "%");
        } else {
            decimal = (BigDecimal) CRUDTemplate.executeQuery(sql, "total", "%%");
        }
        return decimal.intValue();
    }

}
