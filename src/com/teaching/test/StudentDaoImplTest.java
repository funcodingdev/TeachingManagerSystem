package com.teaching.test;

import com.teaching.dao.DaoFactory;
import com.teaching.dao.impl.TeachingTaskImpl;
import com.teaching.domain.Student;
import com.teaching.jdbc.util.CRUDTemplate;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class StudentDaoImplTest {

    @Test
    public void getAllStudent() {
        System.out.println(new TeachingTaskImpl().getTeachingTasks("00"));

    }
}