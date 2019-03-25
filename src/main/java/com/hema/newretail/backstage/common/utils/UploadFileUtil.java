package com.hema.newretail.backstage.common.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.hema.newretail.CloudBohhApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;

import static com.hema.newretail.backstage.common.utils.ossutil.AliyunOSSClientUtil.getContentType;
import static com.hema.newretail.backstage.common.utils.ossutil.OSSClientConstants.*;


/**
 * @author ADMIN
 */
public class UploadFileUtil {

    private static final String IMAGE = "jpg, png, jpeg";

    private static final int SIZE = 10 * 1024 * 1024;
    private static final int SIZE1 = 2 * 1024 * 1024;

    private static final String PROPORTION_IMAGE = "请上传正确比例图片";

    private static final String FILE_PATH = "https://newretail.hemaapp.com/img/";
    private static final String DOMAIN_NAME_URL = "https://newretail.hemaapp.com/";

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 上传图片到OSS
     */
    public static String uploadImageOss(MultipartFile file, Integer proportionType) {
        System.out.println(proportionType);
        String filePath = null;

        if (file.isEmpty()) {
            return "请上传图片";
        }
        try {
            String fileName = file.getOriginalFilename();
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (IMAGE.indexOf(extensionName) == -1) {
                return "请上传图片文件";
            }
            BufferedImage read = ImageIO.read(file.getInputStream());
            // 宽度
            BigDecimal width = new BigDecimal(read.getWidth());
            // 高度
            BigDecimal height = new BigDecimal(read.getHeight());
            BigDecimal divide = width.divide(height, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_DOWN);
            double doubleValue = divide.doubleValue();
            /**
             *  small_pic 1:1 type 1
             *  middle_pic:2:1 type 2
             *  big_pic:3:1 type 3
             *  any_pic:任意比例
             *
             *  5:2 proportionType 5
             *  4:7 proportionType 6
             *
             */
            switch (proportionType) {
                case 1:
                    double d = 1.00;
                    if (doubleValue != d) {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 2:
                    double d1 = 2;
                    if (d1 == doubleValue) {
                        System.out.println("2:1");
                    } else {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 3:
                    double d3 = 3;
                    if (d3 == doubleValue) {
                        System.out.println("3:1");
                    } else {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 4:
                    System.out.println("任意大小图片");
                    break;
                case 5:
                    double p = 2.5D;
                    if (p == doubleValue) {
                        System.out.println("5:2");
                    } else {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 6:
                    double d5 = 0.57;
                    double d6 = 0.58;
                    if (d5 <= doubleValue && doubleValue <= d6) {
                        System.out.println("4:7");
                    } else {
                        return PROPORTION_IMAGE;
                    }
                    break;
                default:
            }

            String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
            int fileSize = (int) file.getSize();
            System.out.println(newFileName + "-->" + fileSize);
            if (fileSize > SIZE1) {
                return "文件大小不能超过2M";
            }
//            if (1 == proportionType || 5 == proportionType || 6 == proportionType) {
//                if (fileSize > SIZE1) {
//                    return "文件大小不能超过2M";
//                }
//            }
//            if (fileSize > SIZE) {
//                return "文件大小不能超过10M";
//            }

            //上传至OSS云存储
            InputStream inputStream = file.getInputStream();
            OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(inputStream.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(newFileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + newFileName + "/" + fileSize + "Byte.");
            PutObjectResult por = ossClient.putObject(BACKET_NAME, FOLDER + newFileName, inputStream, metadata);
            if (!por.getETag().isEmpty()) {
                filePath = FILE_PATH + newFileName;
            } else {
                filePath = "上传失败";
            }
            // 关闭OSSClient。
            ossClient.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 上传文件到bucket
     *
     * @param file     本地文件
     * @param dir      bucket存放目录(末尾要加/)
     * @param fileName 上传文件名
     * @return 访问地址
     */
    public static String uploadLocalFile(File file, String dir, String fileName) {
        if (file == null || !file.exists()) {
            return null;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BACKET_NAME, dir + fileName, file));
            if (null != result) {
                return DOMAIN_NAME_URL + dir + fileName;
            } else {
                return null;
            }
        } catch (OSSException | ClientException oe) {
            logger.error("上传OSS失败:", oe);
            oe.printStackTrace();
            return null;
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
    }
}
