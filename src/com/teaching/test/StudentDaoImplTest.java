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
        System.out.println(DaoFactory.getTeachingTaskDao().getTeaTeachingTasks("200000913","",1,20));
    }
}