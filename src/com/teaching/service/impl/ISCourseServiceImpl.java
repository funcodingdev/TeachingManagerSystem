package com.teaching.service.impl;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.ISCourseDao;
import com.teaching.domain.SCourse;
import com.teaching.service.ISCourseService;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public class ISCourseServiceImpl implements ISCourseService {

    private ISCourseDao sCouseDao = null;

    @Override
    public boolean insertScInfo(SCourse sc) {
        sCouseDao = DaoFactory.getSCourseDao();
        int result = sCouseDao.insertScInfo(sc);
        return result > 0;
    }

    @Override
    public boolean retireScInfo(SCourse sc) {
        sCouseDao = DaoFactory.getSCourseDao();
        int result = sCouseDao.retireScInfo(sc);
        return result > 0;
    }
}
