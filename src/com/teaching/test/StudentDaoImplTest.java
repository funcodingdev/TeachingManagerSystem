package com.teaching.test;

import com.teaching.dao.DaoFactory;
import com.teaching.domain.Grade;
import com.teaching.domain.TeachingTask;
import com.teaching.util.DateUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: fangju
 * @Date: 2019/6/24
 */
public class StudentDaoImplTest {

    @Test
    public void getAllStudent() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        Timestamp timestamp = new Timestamp(new Date().getTime());

        System.out.println(timestamp);

        TeachingTask teachingTask = new TeachingTask("1","1","1","1",timestamp);
        System.out.println(teachingTask.toString());
    }
}