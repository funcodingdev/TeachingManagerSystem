package com.teaching.domain;

import java.io.Serializable;

/**
 * 学生实体类
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class Student implements Serializable {
    private String id;
    private String name;
    private String sex;
    private Number age;
    private String department;
    private String sclass;
    private String phone;
    private String image;
    private String password;

    public Student() {
    }

    public Student(String id, String name, String sex, Number age, String department, String sclass, String password) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.department = department;
        this.sclass = sclass;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", sclass='" + sclass + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
