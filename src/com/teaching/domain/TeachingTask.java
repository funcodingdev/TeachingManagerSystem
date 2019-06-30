package com.teaching.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.teaching.util.DateUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 教学任务实体类
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class TeachingTask implements Serializable {
    private String teachingTaskNum;//教学任务号
    private String courseId;//课程编号
    private String courseName;//课程名
    private String teacherId;//教师编号
    private String teacherName;//教师姓名
    private Number totalNum;//选课总人数
    private String location;//上课地点
    private Timestamp startTime;//开课时间

    public TeachingTask() {
    }

    public TeachingTask(String teachingTaskNum, String courseName, String teacherId, String location,Timestamp startTime) {
        this.teachingTaskNum = teachingTaskNum;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.location = location;
        this.startTime = startTime;
    }

    public String getTeachingTaskNum() {
        return teachingTaskNum;
    }

    public void setTeachingTaskNum(String teachingTaskNum) {
        this.teachingTaskNum = teachingTaskNum;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Number getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Number totalNum) {
        this.totalNum = totalNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "{" +
                "teachingTaskNum='" + teachingTaskNum + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", totalNum=" + totalNum +
                ", location='" + location + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
