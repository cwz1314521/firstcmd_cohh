package com.hema.newretail.backstage.common.utils.rongyun;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Map;
/**
 * @Department 新零售
 * @ClassName RongCloudUtil
 * @Description 融云
 * @Author ---CWZ
 * @Date 2018/10/26 17:12
 * @Version 1.0
 **/
public class RongCloudUtil {
    /**
     * // 申请的融云key
     */
    private final static String APPKEY = "pkfcgjstpol18";
    /**
     * // 申请的的云secret
     */
    private final static String APP_SECRET = "UUBf1kzU3Sx1l2";

    /**
     * // sha1加密产参数
     */
    private final static int[] ABCDE = {0x67452301, 0xefcdab89,
            0x98badcfe, 0x10325476, 0xc3d2e1f0};

    /**
     * // 摘要数据存储数组
     */
    private static int[] digestInt = new int[5];

    /**
     * // 计算过程中的临时数据存储数组
     */
    private static int[] tmpData = new int[80];
    private static final Integer SIXTEEN = 16;
    private static final Integer FIFTY_SIX = 56;
    private static final Integer SEVENTY_NINE = 79;
    private static final Integer NINETEEN = 19;
    private static final Integer TWENTY = 20;
    private static final Integer THIRTY_NINE = 39;
    private static final Integer FORTY = 40;
    private static final Integer FIFTY_NINE = 59;
    private static final Integer SIXTY = 60;
    private static final Integer TWOHUNDRED = 200;


    /**
     * // 计算sha-1摘要
     *
     * @param bytedata
     * @return
     */
    private static int processInputBytes(byte[] bytedata) {
        // 初试化常量
        System.arraycopy(ABCDE, 0, digestInt, 0, ABCDE.length);
        // 格式化输入字节数组，补10及长度数据
        byte[] newbyte = byteArrayFormatData(bytedata);
        // 获取数据摘要计算的数据单元个数
        int MCount = newbyte.length / 64;
        // 循环对每个数据单元进行摘要计算
        for (int pos = 0; pos < MCount; pos++) {
            // 将每个单元的数据转换成16个整型数据，并保存到tmpData的前16个数组元素中
            for (int j = 0; j < SIXTEEN; j++) {
                tmpData[j] = byteArrayToInt(newbyte, (pos * 64) + (j * 4));
            }
            // 摘要计算函数
            encrypt();
        }
        return 20;
    }

    /**
     * // 格式化输入字节数组格式
     *
     * @param bytedata
     * @return
     */
    private static byte[] byteArrayFormatData(byte[] bytedata) {
        // 补0数量
        int zeros = 0;
        // 补位后总位数
        int size = 0;
        // 原始数据长度
        int n = bytedata.length;
        // 模64后的剩余位数
        int m = n % 64;
        // 计算添加0的个数以及添加10后的总长度
        if (m < FIFTY_SIX) {
            zeros = 55 - m;
            size = n - m + 64;
        } else if (m == FIFTY_SIX) {
            zeros = 63;
            size = n + 8 + 64;
        } else {
            zeros = 63 - m + 56;
            size = (n + 64) - m + 64;
        }
        // 补位后生成的新数组内容
        byte[] newbyte = new byte[size];
        // 复制数组的前面部分
        System.arraycopy(bytedata, 0, newbyte, 0, n);
        // 获得数组Append数据元素的位置
        int l = n;
        // 补1操作
        newbyte[l++] = (byte) 0x80;
        // 补0操作
        for (int i = 0; i < zeros; i++) {
            newbyte[l++] = (byte) 0x00;
        }
        // 计算数据长度，补数据长度位共8字节，长整型
        long N = (long) n * 8;
        byte h8 = (byte) (N & 0xFF);
        byte h7 = (byte) ((N >> 8) & 0xFF);
        byte h6 = (byte) ((N >> 16) & 0xFF);
        byte h5 = (byte) ((N >> 24) & 0xFF);
        byte h4 = (byte) ((N >> 32) & 0xFF);
        byte h3 = (byte) ((N >> 40) & 0xFF);
        byte h2 = (byte) ((N >> 48) & 0xFF);
        byte h1 = (byte) (N >> 56);
        newbyte[l++] = h1;
        newbyte[l++] = h2;
        newbyte[l++] = h3;
        newbyte[l++] = h4;
        newbyte[l++] = h5;
        newbyte[l++] = h6;
        newbyte[l++] = h7;
        newbyte[l++] = h8;
        return newbyte;
    }

    private static int f1(int x, int y, int z) {
        return (x & y) | (~x & z);
    }

    private static int f2(int x, int y, int z) {
        return x ^ y ^ z;
    }

    private static int f3(int x, int y, int z) {
        return (x & y) | (x & z) | (y & z);
    }

    private static int f4(int x, int y) {
        return (x << y) | x >>> (32 - y);
    }

    /**
     * 单元摘要计算函数
     */
    private static void encrypt() {
        for (int i = SIXTEEN; i <= SEVENTY_NINE; i++) {
            tmpData[i] = f4(tmpData[i - 3] ^ tmpData[i - 8] ^ tmpData[i - 14]
                    ^ tmpData[i - 16], 1);
        }
        int[] tmpabcde = new int[5];
        for (int i1 = 0; i1 < tmpabcde.length; i1++) {
            tmpabcde[i1] = digestInt[i1];
        }
        for (int j = 0; j <= NINETEEN; j++) {
            int tmp = f4(tmpabcde[0], 5)
                    + f1(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
                    + tmpData[j] + 0x5a827999;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int k = TWENTY; k <= THIRTY_NINE; k++) {
            int tmp = f4(tmpabcde[0], 5)
                    + f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
                    + tmpData[k] + 0x6ed9eba1;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int l = FORTY; l <= FIFTY_NINE; l++) {
            int tmp = f4(tmpabcde[0], 5)
                    + f3(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
                    + tmpData[l] + 0x8f1bbcdc;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int m = SIXTY; m <= SEVENTY_NINE; m++) {
            int tmp = f4(tmpabcde[0], 5)
                    + f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4]
                    + tmpData[m] + 0xca62c1d6;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int i2 = 0; i2 < tmpabcde.length; i2++) {
            digestInt[i2] = digestInt[i2] + tmpabcde[i2];
        }
        for (int n = 0; n < tmpData.length; n++) {
            tmpData[n] = 0;
        }
    }


    /**
     * 4字节数组转换为整数
     *
     * @param bytedata
     * @param i
     * @return
     */
    private static int byteArrayToInt(byte[] bytedata, int i) {
        return ((bytedata[i] & 0xff) << 24) | ((bytedata[i + 1] & 0xff) << 16)
                | ((bytedata[i + 2] & 0xff) << 8) | (bytedata[i + 3] & 0xff);
    }

    /**
     * // 整数转换为4字节数组
     *
     * @param intValue
     * @param byteData
     * @param i
     */
    private static void intToByteArray(int intValue, byte[] byteData, int i) {
        byteData[i] = (byte) (intValue >>> 24);
        byteData[i + 1] = (byte) (intValue >>> 16);
        byteData[i + 2] = (byte) (intValue >>> 8);
        byteData[i + 3] = (byte) intValue;
    }


    /**
     * // 将字节转换为十六进制字符串
     *
     * @param ib
     * @return
     */
    private static String byteToHexString(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }


    /**
     * // 将字节数组转换为十六进制字符串
     *
     * @param bytearray
     * @return
     */
    private static String byteArrayToHexString(byte[] bytearray) {
        String strDigest = "";
        for (int i = 0; i < bytearray.length; i++) {
            strDigest += byteToHexString(bytearray[i]);
        }
        return strDigest;
    }


    /**
     * 计算sha-1摘要，返回相应的字节数组
     *
     * @param byteData
     * @return
     */
    public static byte[] getDigestOfBytes(byte[] byteData) {
        processInputBytes(byteData);
        byte[] digest = new byte[20];
        for (int i = 0; i < digestInt.length; i++) {
            intToByteArray(digestInt[i], digest, i * 4);
        }
        return digest;
    }

    /**
     * 计算sha-1摘要，返回相应的十六进制字符串
     *
     * @param byteData
     * @return
     */
    public static String getDigestOfString(byte[] byteData) {
        return byteArrayToHexString(getDigestOfBytes(byteData));
    }

    /**
     * 发送post请求
     *
     * @param path    url地址
     * @param params  参数集合
     * @param encode  请求编码
     * @param timeout 超时时间（秒）
     * @return byte[] byte数组
     * @throws Exception
     */
    public static byte[] post(String path, Map<String, String> params,
                              String encode, int timeout) throws Exception {
        byte[] resultBuffer = null;
        Double nonce = Math.floor(Math.random() * 100000 + 100000);
        Long timestamp = Timestamp.valueOf("2015-3-18 00:00:00").getTime();
        String signature = getDigestOfString((APP_SECRET + nonce + timestamp)
                .getBytes());
        StringBuilder parambuilder = new StringBuilder("");
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parambuilder.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            parambuilder.deleteCharAt(parambuilder.length() - 1);
        }
        byte[] data = parambuilder.toString().getBytes();
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(timeout * 1000);
        conn.setReadTimeout(timeout * 1000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty(
                "Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, " +
                        "application/x-shockwave-flash, application/xaml+xml, " +
                        "application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
                        "application/x-ms-application, application/vnd.ms-excel, " +
                        "application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("App-Key", APPKEY);
        conn.setRequestProperty("Nonce", nonce + "");
        conn.setRequestProperty("Timestamp", timestamp + "");
        conn.setRequestProperty("Signature", signature);
        conn.setRequestProperty(
                "User-Agent",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; " +
                        ".NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; " +
                        ".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setRequestProperty("Connection", "Keep-Alive");
        DataOutputStream outStream = new DataOutputStream(
                conn.getOutputStream());
        outStream.write(data);
        outStream.flush();
        outStream.close();
        if (conn.getResponseCode() == TWOHUNDRED) {
            resultBuffer = readStream(conn.getInputStream());
        }
        conn.disconnect();
        return resultBuffer;
    }

    /**
     * 解析输入流
     *
     * @param inStream 输入流
     * @return byte[] byte数组
     * @throws Exception
     */
    private static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


}

