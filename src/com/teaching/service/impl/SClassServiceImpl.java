package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.ISClassDao;
import com.teaching.domain.SClass;
import com.teaching.service.ISClassService;

import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class SClassServiceImpl implements ISClassService {

    private ISClassDao classDao = null;

    @Override
    public List<SClass> listAllSClass(String deptId) {
        classDao = DaoFactory.getSClassDao();
        List<SClass> allClass = classDao.listAllSClass(deptId);
        return allClass;
    }
}
