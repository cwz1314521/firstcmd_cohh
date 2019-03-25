package com.hema.newretail.backstage.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author Created by jiahao
 * @Date 2018/10/18 10:16
 */
@Configuration
@ConfigurationProperties(prefix="weixin.open")
@Component
public class WxPayProperties {

    @Value("${weixin.open.domain}")
    private String domain;

    @Value("${weixin.open.appId}")
    private String appId;

    @Value("${weixin.open.appSecret}")
    private String appSecret;

    @Value("${weixin.open.appKey}")
    private String appKey;

    @Value("${weixin.open.mchId}")
    private String mchId;

    @Value("${weixin.open.cert}")
    private String cert;

    @Value("${weixin.open.urlUnifiedOrder}")
    private String urlUnifiedOrder;

    @Value("${weixin.open.urlRefund}")
    private String urlRefund;

    @Value("${weixin.open.urlNotify}")
    private String urlNotify;

    @Value("${weixin.open.urlNotifyRefund}")
    private String urlNotifyRefund;


    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getUrlUnifiedOrder() {
        return urlUnifiedOrder;
    }

    public void setUrlUnifiedOrder(String urlUnifiedOrder) {
        this.urlUnifiedOrder = urlUnifiedOrder;
    }

    public String getUrlRefund() {
        return urlRefund;
    }

    public void setUrlRefund(String urlRefund) {
        this.urlRefund = urlRefund;
    }

    public String getUrlNotify() {
        return urlNotify;
    }

    public void setUrlNotify(String urlNotify) {
        this.urlNotify = urlNotify;
    }

    public String getUrlNotifyRefund() {
        return urlNotifyRefund;
    }

    public void setUrlNotifyRefund(String urlNotifyRefund) {
        this.urlNotifyRefund = urlNotifyRefund;
    }
}
