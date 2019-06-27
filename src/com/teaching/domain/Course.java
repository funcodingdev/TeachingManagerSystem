package com.teaching.domain;

import java.io.Serializable;

/**
 * 课程实体类
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class Course implements Serializable {
    private String id;
    private String name;
    private Number credit;
    private Number period;

    public Course() {
    }

    public Course(String id, String name, Number credit, Number period) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.period = period;
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

    public Number getCredit() {
        return credit;
    }

    public void setCredit(Number credit) {
        this.credit = credit;
    }

    public Number getPeriod() {
        return period;
    }

    public void setPeriod(Number period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", period=" + period +
                '}';
    }
}
