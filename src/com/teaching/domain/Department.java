package com.teaching.domain;

import java.io.Serializable;

/**
 * 部门实体类
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class Department implements Serializable {
    private String id;
    private String name;

    public Department(){

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

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
