package com.hema.newretail.backstage.common.utils;


import com.hema.newretail.CloudBohhApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Department 新零售
 * @ClassName NumberUtils
 * @Description 编号规则公共类
 * @Author ---CWZ
 * @Date 2018/10/31 15:23
 * @Version 1.0
 **/
public class NumberUtils {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private static final String CK ="CK";

    /**
     *
     * 功能描述: 年份+类型（04：原料供应商）+当年入驻加盟的原料供应商数目（4位）
     *
     * @param: String
     * @return: String
     * @author: cwz
     * @date: 2018/10/31 15:25
     */
    public static String manufacturer(Integer number){
        StringBuffer result=new StringBuffer();
        String year =String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String substring = year.substring(year.length() - 2, year.length());
        result.append(substring).append("04").append(String.format("%04d", number));
        logger.info("执行原料厂商编号拼装......"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"04"+number);
        return result.toString();
    }

    /**
     *-----------------溯源系统中配料预订订单单号
     * 功能描述:原料供应商编号+年月日+生成的订单序号（3位）
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/1 10:24
     */
    public static String orderCode(String code, Integer num){
        StringBuffer result = new StringBuffer();
        result.append(code).append(new SimpleDateFormat("yyMMdd").format(new Date())).append(String.format("%03d", num));
        return result.toString();
    }
    /**
     *
     * 功能描述: 出库单号规则定义：CK+年月日+3位数字+1位随机数；例：CK201802010012
     *
     * @param  
     * @return  
     * @author  cwz
     * @date  2018/12/11 17:11
     */

    public static String deviceOutStore(Integer num){
        StringBuffer result = new StringBuffer();
        Random rand = new Random();
        if(num == null){
            result.append(CK).append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        }else {
            result.append(CK).append(new SimpleDateFormat("yyyyMMdd").format(new Date())).append(String.format("%03d", num)).append(rand.nextInt(10));
        }
        return result.toString();
    }
}
