package com.hema.newretail.backstage.common.utils;

import com.hema.newretail.backstage.common.WxPayProperties;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.*;

/**
 * hema-newetaril-com.hema.newretail.common.wxpay.utils
 *
 * @Description:
 * @Author:
 * @Date: 2018-08-02 17:50
 */
public class PayUtil {

    private final Logger logger = LoggerFactory.getLogger(PayUtil.class);

    WxPayProperties wxPayProperties;

    public PayUtil(WxPayProperties wxPayProperties) {
        this.wxPayProperties = wxPayProperties;
    }


    /**
     * 退款函数，该方法可以对曾经部分退款的订单进行再次退款
     *
     * @param outTradeNo  商户订单号
     * @param allTotalFee 退款对应的订单的总金额（以“元”为单位）
     * @param refundFee   计划退款的金额（以“元”为单位）
     * @return
     * @throws Exception
     */
    public Map<String, String> wechatRefund(String outTradeNo, Long allTotalFee, Long refundFee,
                                            String refundDesc, String orderId) throws Exception {
        SortedMap<String, Object> params = new TreeMap<>();
        params.put("refund_desc", refundDesc);
        params.put("appid", wxPayProperties.getAppId());
        params.put("mch_id", wxPayProperties.getMchId());
        params.put("op_user_id", wxPayProperties.getMchId());
        params.put("nonce_str", StringUtil.getRandomStringByLength(24));
        params.put("out_trade_no", outTradeNo);
        params.put("out_refund_no", orderId);
        params.put("total_fee", String.valueOf(allTotalFee));
        params.put("refund_fee", String.valueOf(refundFee));
        params.put("notify_url", wxPayProperties.getUrlNotifyRefund());
        String sign = getSign(params);
        params.put("sign", sign);
        String xml = XmlHelper.mapToXml(params);
        Map<String, String> stringStringMap = doRefund(wxPayProperties.getUrlRefund(), xml, wxPayProperties.getMchId());
        return stringStringMap;
    }

    /**
     * 退款
     */
    public Map<String, String> doRefund(String url, String data, String partner) throws Exception {
        /**
         * p12证书的位置
         * 微信公众平台：“微信支付”--》“商户信息”--》“交易数据”--》“详情请登录微信支付商户平台查看”（登录）--》
         * “API安全”--》“API证书”--》“下载证书”
         * 下载证书后将apiclient_cert.p12放在src目录下面（出于安全考虑，请自行下载自己的证书）
         */
        /*P12文件目录
        URL url2 = PayUtil.class.getClassLoader().getResource(apiclient_certLocation);
        URI uri = url2.toURI();
        System.out.println("url2:" + uri.toString());
        FileInputStream instream = new FileInputStream(new File(uri));*/
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream resourceAsStream = PayUtil.class.getResourceAsStream(wxPayProperties.getCert());
        try {
            keyStore.load(resourceAsStream, partner.toCharArray());
        } finally {
            resourceAsStream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, partner.toCharArray()).build();
        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"},
                null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslSf).build();
        try {
            // 设置响应头信息
            HttpPost httPost = new HttpPost(url);
            httPost.addHeader("Connection", "keep-alive");
            httPost.addHeader("Accept", "*/*");
            httPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httPost.addHeader("Host", "api.mch.weixin.qq.com");
            httPost.addHeader("X-Requested-With", "XMLHttpRequest");
            httPost.addHeader("Cache-Control", "max-age=0");
            httPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httPost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httPost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                logger.info(jsonStr);
                EntityUtils.consume(entity);
                return XmlHelper.xmlToMap(jsonStr);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }


    /**
     * 验证回调签名
     *
     * @param map
     * @return
     */
    public boolean isTenpaySign(Map<String, String> map) {
        logger.info("@params:" + map.toString());
        String signFromAPIResponse = map.get("sign");
        if (signFromAPIResponse == null || "".equals(signFromAPIResponse)) {
            logger.info("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        logger.info("服务器回包里面的签名是:" + signFromAPIResponse);
        // 过滤空 设置 TreeMap
        SortedMap<String, Object> packageParams = new TreeMap();

        for (String parameter : map.keySet()) {
            String parameterValue = map.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }

        String resultSign = getSign(packageParams);
        logger.info("@resultSign:" + resultSign);

        return signFromAPIResponse.equals(resultSign);
    }

    /**
     * 获取签名 md5加密(微信支付必须用MD5加密) 获取支付签名
     */
    public String getSign(SortedMap<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        // 所有参与传参的参数按照accsii排序（升序）
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        //key后面接商户号的秘钥secret
        sb.append("key=" + wxPayProperties.getAppKey());
        logger.info("@sgin:" + sb.toString());
        String sign = Md5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }

}
