package com.hema.newretail.backstage.common.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Department 新零售
 * @ClassName StringUtil
 * @Description 字符串处理公共类
 * @Author ---CWZ
 * @Date 2018/10/13 10:57
 * @Version 1.0
 **/
public class StringUtil {

    private static final String HTTPS ="https:";


    /**
     *
     * 功能描述:
     *
     * @param: 逗号隔开的字符串
     * @return: list
     * @author: admin
     * @date: 2018/10/13 11:10
     */
    public static List<String> stringsToString(String data){
        List<String> idList = Arrays.asList(data.split(","));
        return idList;
    }

    /**
     *
     * 功能描述:
     *
     * @param: 逗号隔开的字符串
     * @return: list
     * @author: admin
     * @date: 2018/10/13 11:10
     */
    public static List<Integer> stringsToInteger(String data){
        List<String> idList = Arrays.asList(data.split(","));
        List<Integer> integers = new ArrayList<>();
        for (String a:idList
             ) {
            integers.add(Integer.parseInt(a));
        }
        return integers;
    }
    /**
     *
     * 功能描述:
     *
     * @param: 逗号隔开的字符串
     * @return: list
     * @author: cwz
     * @date: 2018/10/13 11:10
     */
    public static List<Long> stringsToLong(String data){
        List<String> idList = Arrays.asList(data.split(","));
        List<Long> longs = new ArrayList<>();
        for (String a:idList
                ) {
            longs.add(Long.parseLong(a));
        }
        return longs;
    }

    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
