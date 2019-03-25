package com.hema.newretail.backstage.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {


    public static Date getDate(String datetime)throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(datetime);
    }

    public static String getStringByDate(Date datetime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(datetime);
    }

    public static String getStringByDateSix(Date datetime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(datetime);
    }

    public static String getStringByDateFormart(Date datetime,String formart){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
        return dateFormat.format(datetime);
    }

    public static Date stringToDate(String datetime,String formart) throws Exception{
        SimpleDateFormat formatter=new SimpleDateFormat(formart);
        return formatter.parse(datetime);
    }

    public static Date threeMonthAgo(Date datetime){
        Calendar c = Calendar.getInstance();
        c.setTime(datetime);
        c.add(Calendar.MONTH, -3);
        return c.getTime();
    }

    /**
     *
     * 功能描述: 时间段转化
     *
     * @param: long
     * @return: String
     * @author: cwz
     * @date: 2018/10/15 9:51
     */
    public static String timeQuantum(Long time){
        StringBuffer times = new StringBuffer();
        if(time !=null){
            Long days = time/(1000*60*60*24);
            Long hours = (time%(1000*60*60*24))/(60*60*1000);
            Long minutes = ((time%(1000*60*60*24))%(60*60*1000))/(60*1000);
            Long seconds = (((time%(1000*60*60*24))%(60*60*1000))%(60*1000))/1000;
            if(days > 0){
                times.append(days).append(" 天 ");
            }
            if(hours > 0){
                times.append(hours).append(" 时 ");
            }
            if(minutes > 0){
                times.append(minutes).append(" 分 ");
            }
            if(seconds > 0){
                times.append(seconds).append(" 秒 ");
            }
            return times.toString();

        }else{
            return null;
        }
    }

    public static Date getEndTime(Date date) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.set(Calendar.HOUR, 23);
        time.set(Calendar.MINUTE, 59);
        time.set(Calendar.SECOND, 59);
        time.set(Calendar.MILLISECOND, 999);
        return time.getTime();
    }

    /**
     * 日期转星期几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
