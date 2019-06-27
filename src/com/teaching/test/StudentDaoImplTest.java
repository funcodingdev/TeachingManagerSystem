package com.teaching.test;

import com.teaching.dao.DaoFactory;
import org.junit.Test;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class StudentDaoImplTest {

    @Test
    public void getAllStudent() {
        System.out.println(DaoFactory.getStudentDao().getSCGrade("201962502"));
    }
}