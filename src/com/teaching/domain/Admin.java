package com.teaching.domain;


import java.io.Serializable;

/**
 * @Author: fangju
 * @Date: 2019/5/25 11:55
 * 管理员实体类
 */
public class Admin implements Serializable {
    private String id;//管理员编号

    private String password;//密码

    private String name;//姓名

    private String sex;//性别

    private Number age;//年龄

    private String image;//头像

    public Admin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", image='" + image + '\'' +
                '}';
    }
}
