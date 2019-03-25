package com.hema.newretail.backstage.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * @Author Created by jiahao
 * @Date 2018/9/25 17:23
 */
public class AESUtil {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";

    /**
     * 生成key
     */
    /*private static SecretKeySpec key = new SecretKeySpec(MD5Util.MD5Encode(
            APP_KEY, "UTF-8").toLowerCase().getBytes(), ALGORITHM);*/

    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(String data, String appKey) throws Exception {
        SecretKeySpec key = key(appKey);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64Util.encode(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     * @param base64Data
     * @return
     * @throws Exception
     */
    public static String decryptData(String base64Data, String appKey) throws Exception {
        SecretKeySpec key = key(appKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64Util.decode(base64Data)));
    }

    private static SecretKeySpec key(String appKey) {
        SecretKeySpec key = new SecretKeySpec(Md5Util.MD5Encode(
                appKey, "UTF-8").toLowerCase().getBytes(), ALGORITHM);
        return key;
    }
}
