package com.teaching.domain;

import java.io.Serializable;

/**
 * 班级实体类
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class SClass implements Serializable {
    private String name;
    private Number totalNum;
    private String dept;

    public SClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Number totalNum) {
        this.totalNum = totalNum;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", totalNum=" + totalNum +
                ", dept='" + dept + '\'' +
                '}';
    }
}
