package com.teaching.dao;

import com.teaching.domain.SClass;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public interface ISClassDao {
    List<SClass> getAllClass(String deptId);
}
