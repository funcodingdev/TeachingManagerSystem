package com.teaching.dao;

import com.teaching.domain.SCourse;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ISCourseDao {
    int insertScInfo(SCourse sc);
    int retireScInfo(SCourse sc);
}
