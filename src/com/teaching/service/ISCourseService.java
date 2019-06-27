package com.teaching.service;

import com.teaching.domain.SCourse;

/**
 * @Author: fangju
 * @Date: 2019/6/25
 */
public interface ISCourseService {
    boolean insertScInfo(SCourse sc);
    boolean retireScInfo(SCourse sc);
}
