package com.teaching.domain;

import java.io.Serializable;

/**
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
