package com.hema.newretail.backstage.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.utils
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-22 13:03
 */
public class IpUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = null;
        try {
            // 获取用户真实的地址
            String xip = request.getHeader("X-Real-IP");
            // 获取多次代理后的IP字符串值
            String xfor = request.getHeader("X-Forwarded-For");
            if (!isInvalidIpAddress(xfor)) {
                // 多次反向代理后会有多个IP值，第一个用户真实的IP地址
                int index = xfor.indexOf(",");
                if (index >= 0) {
                    return xfor.substring(0, index);
                } else {
                    return xfor;
                }
            }
            ip = xip;
            if (isInvalidIpAddress(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (isInvalidIpAddress(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (isInvalidIpAddress(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (isInvalidIpAddress(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (isInvalidIpAddress(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 校验是否无效的ip地址
     *
     * @param ipAddress ip地址
     * @return true无效 false有效
     */
    private static boolean isInvalidIpAddress(String ipAddress) {
        return ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress);
    }
}
