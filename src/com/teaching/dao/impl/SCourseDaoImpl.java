package com.teaching.dao.impl;

import com.teaching.dao.ISCourseDao;
import com.teaching.domain.SCourse;
import com.teaching.jdbc.util.CRUDTemplate;
import com.teaching.jdbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class SCourseDaoImpl implements ISCourseDao {

    @Override
    public int insertScInfo(SCourse sc) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs= null;
        try {
            String sql_insert = "insert into selectCourse(stuId,teachingTaskNum,grade) values(?,?,?)";
            conn = JDBCUtil.getConn();
            conn.setAutoCommit(false);
            psmt = conn.prepareStatement(sql_insert);
            psmt.setString(1,sc.getStuId());
            psmt.setString(2,sc.getTeachingTaskNum());
            psmt.setObject(3,sc.getGrade());
            if(psmt.executeUpdate() > 0){
                String sql_update = "update teachingTask set totalNum = totalNum+1 where teachingTaskNum = ?";
                psmt = conn.prepareStatement(sql_update);
                psmt.setString(1,sc.getTeachingTaskNum());
                return psmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn != null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                JDBCUtil.close(conn,psmt,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int retireScInfo(SCourse sc) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs= null;
        try {
            String sql_delete = "delete from selectCourse where stuId = ? and teachingTaskNum = ?";
            conn = JDBCUtil.getConn();
            conn.setAutoCommit(false);
            psmt = conn.prepareStatement(sql_delete);
            psmt.setString(1,sc.getStuId());
            psmt.setString(2,sc.getTeachingTaskNum());
            if(psmt.executeUpdate() > 0){
                String sql_update = "update teachingTask set totalNum = totalNum-1 where teachingTaskNum = ?";
                psmt = conn.prepareStatement(sql_update);
                psmt.setString(1,sc.getTeachingTaskNum());
                return psmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if(conn != null){
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                JDBCUtil.close(conn,psmt,rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
