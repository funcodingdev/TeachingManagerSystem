package com.teaching.dao.impl;

import com.teaching.dao.IStudentDao;
import com.teaching.domain.Grade;
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
    public List<Student> listAllStudents() {
        String sql = "select * from student";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class));
    }

    @Override
    public List<Student> listStudents(String keyWord) {
        String sql = "select * from student where id like '%" + keyWord + "%'";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class));
    }

    @Override
    public List<Student> listStudents(int start, int end) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,stu.* FROM (SELECT * FROM student ORDER BY id ASC) stu WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class), end, start);
    }

    @Override
    public List<Student> listStudents(String keyWord, Integer pageStart, Integer pageEnd) {
        String sql = "SELECT * FROM(SELECT ROWNUM NO,stu.* FROM (SELECT * FROM student where id like '%" + keyWord + "%' ORDER BY id ASC) stu WHERE ROWNUM<=?) WHERE NO >=?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Student.class), pageEnd, pageStart);
    }

    @Override
    public List<Grade> getSCGrade(String teachingTaskNum) {
        String sql = "select selectCourse.*,Student.name,Student.department,Student.sClass,selectCourse.grade from selectCourse inner join Student on selectCourse.stuId = Student.id where teachingTaskNum = ?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Grade.class), teachingTaskNum);
    }

    @Override
    public int updateSCGrade(Grade grade) {
        String sql = "update selectCourse set grade = ? where stuId = ? and teachingTaskNum = ?";
        return CRUDTemplate.executeUpdate(sql, grade.getGrade(), grade.getStuId(), grade.getTeachingTaskNum());
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
        String sql = "update student set age = ? , phone = ? where id = ?";
        return CRUDTemplate.executeUpdate(sql, stu.getAge(), stu.getPhone(), stu.getId());
    }

    @Override
    public int updateStuPassword(String stuId, String oldPassword, String newPassword) {
        String sql = "update student set password = ? where id = ? and password = ?";
        return CRUDTemplate.executeUpdate(sql,newPassword,stuId,oldPassword);
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
