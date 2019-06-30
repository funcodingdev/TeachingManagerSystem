package com.teaching.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: fangju
 * @Date: 2019/6/27
 */
public class DateUtil {

    /**
     * 根据当前日期返回时间字符串
     * @param date
     * @return
     */
    public static String getDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 返回时间字符串的时间戳
     * @param timeString
     * @return
     */
    public static long getDateLong(String timeString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(timeString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
