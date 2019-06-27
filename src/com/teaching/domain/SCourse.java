package com.teaching.domain;

/**
 * 选课实体类
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class SCourse {
    private String teachingTaskNum;
    private String stuId;
    private Number grade;

    public SCourse() {
    }

    public SCourse(String teachingTaskNum, String stuId, Number grade) {
        this.teachingTaskNum = teachingTaskNum;
        this.stuId = stuId;
        this.grade = grade;
    }

    public String getTeachingTaskNum() {
        return teachingTaskNum;
    }

    public void setTeachingTaskNum(String teachingTaskNum) {
        this.teachingTaskNum = teachingTaskNum;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Number getGrade() {
        return grade;
    }

    public void setGrade(Number grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "{" +
                "teachingTaskNum='" + teachingTaskNum + '\'' +
                ", stuId='" + stuId + '\'' +
                ", grade=" + grade +
                '}';
    }
}
