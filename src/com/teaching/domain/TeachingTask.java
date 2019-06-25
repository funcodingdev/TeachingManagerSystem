package com.teaching.domain;

import java.io.Serializable;

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

    public TeachingTask() {
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
                '}';
    }
}
