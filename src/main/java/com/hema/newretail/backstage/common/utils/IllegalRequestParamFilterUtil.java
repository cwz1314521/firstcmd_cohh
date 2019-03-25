package com.hema.newretail.backstage.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.utils
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-19 15:09
 */
public class IllegalRequestParamFilterUtil {
    private final static Logger Logger = LoggerFactory.getLogger(IllegalRequestParamFilterUtil.class);
    private static Set<String> notAllowedKeyWords = new HashSet<>(16);
    static {
        String key = "*|%";
//        String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
        String keyStr[] = key.split("\\|");
        notAllowedKeyWords.addAll(Arrays.asList(keyStr));
    }

    /**
     * 对常见的sql注入攻击进行拦截
     *
     * @param sInput
     * @return true 表示参数不存在SQL注入风险
     * false 表示参数存在SQL注入风险
     */
    public static Boolean paramFilter(String sInput) {
        if (sInput == null || sInput.trim().length() == 0) {
            return true;
        }
        sInput = sInput.toUpperCase();
        for (String str : notAllowedKeyWords) {
            if (sInput.contains(str.toUpperCase())) {
                Logger.error("该参数怎么SQL注入风险：sInput=" + sInput);
                return false;
            }
        }
        Logger.info("通过sql检测");
        return true;
    }
}
