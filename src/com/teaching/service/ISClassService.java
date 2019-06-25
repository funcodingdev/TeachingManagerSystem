package com.teaching.service;

import com.alibaba.fastjson.JSONArray;
import com.teaching.domain.SClass;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public interface ISClassService {
    List<SClass> getAllClass(String deptId);
}
