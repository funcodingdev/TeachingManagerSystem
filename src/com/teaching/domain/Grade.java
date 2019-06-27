package com.teaching.domain;

/**
 * 成绩实体类
 * @Author: fangju
 * @Date: 2019/6/27
 */
public class Grade {
    private String stuId;//学号
    private String teachingTaskNum;//教学任务号
    private Number grade;//成绩
    //基本学生信息
    private String name;
    private String department;
    private String sclass;

    public Grade() {
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getTeachingTaskNum() {
        return teachingTaskNum;
    }

    public void setTeachingTaskNum(String teachingTaskNum) {
        this.teachingTaskNum = teachingTaskNum;
    }

    public Number getGrade() {
        return grade;
    }

    public void setGrade(Number grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    @Override
    public String toString() {
        return "{" +
                "stuId='" + stuId + '\'' +
                ", teachingTaskNum='" + teachingTaskNum + '\'' +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", sclass='" + sclass + '\'' +
                '}';
    }
}
