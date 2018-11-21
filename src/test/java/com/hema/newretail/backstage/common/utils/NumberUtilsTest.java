package com.hema.newretail.backstage.common.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Department 新零售
 * @ClassName NumberUtilsTest
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/11/16 9:45
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NumberUtilsTest {

    @Test
    public void manufacturer() {

        System.out.println(NumberUtils.manufacturer(4));
        String year =String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String substring = year.substring(year.length() - 2, year.length());
        System.out.println(substring);
    }

    @Test
    public void orderCode() throws Exception{
//        Date endTime = TimeUtil.getEndTime(TimeUtil.stringToDate("2018-10-10", "yyyy-MM-dd"));
//        String stringByDateFormart = TimeUtil.getStringByDateFormart(endTime, "yyyy-MM-dd HH:mm:ss");
//        System.out.println(stringByDateFormart);
        String data = "sadasdsad.dasdas";
        String substring = data.substring(data.lastIndexOf("."));
        System.out.println(substring);
    }
}