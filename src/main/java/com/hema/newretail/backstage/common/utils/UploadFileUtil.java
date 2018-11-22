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
import java.io.IOException;
import java.io.InputStream;

import static com.hema.newretail.backstage.common.utils.ossutil.AliyunOSSClientUtil.getContentType;
import static com.hema.newretail.backstage.common.utils.ossutil.OSSClientConstants.*;


/**
 * @author jiahao
 */
public class UploadFileUtil {

    private static final String IMAGE = "jpg, png，gif, jpeg";

    private static final int SIZE = 1024000;

    private static final String PROPORTION_IMAGE = "请上传正确比例图片";

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    /**
     * 上传图片到OSS
     */
    public static String uploadImageOss(MultipartFile file, Integer proportionType) {
        System.out.println(proportionType);
        String filePath = null;

        if (file.isEmpty()) {
            return "请上传正确比例图片";
        }
        try {
            String fileName = file.getOriginalFilename();
            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);

            if (IMAGE.indexOf(extensionName) == -1) {
                return "请上传正确比例图片";
            }
            BufferedImage read = ImageIO.read(file.getInputStream());
            int width = read.getWidth();
            int height = read.getHeight();

            /**
             *  small_pic 1:1 type 1
             *  middle_pic:2:1 type 2
             *  big_pic:3:1 type 3
             *  any_pic:任意比例
             */
            switch (proportionType) {
                case 1:
                    if (width != height) {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 2:
                    if (width != height * 2 && height != width * 2) {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 3:
                    if (width != height * 3 && height != width * 3) {
                        return PROPORTION_IMAGE;
                    }
                    break;
                case 4:
                    System.out.println("任意大小图片");
                    break;
                default:
            }
//            while (proportionType == 1) {
//                if (width != height) {
//                    return PROPORTION_IMAGE;
//                }
//            }

            String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
            int fileSize = (int) file.getSize();
            System.out.println(newFileName + "-->" + fileSize);
            if (fileSize > SIZE) {
                return "请上传正确比例图片";
            }

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
                filePath = "https://newretail.hemaapp.com/img/" + newFileName;
            } else {
                filePath = "请上传正确比例图片";
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
                return "https://newretail.hemaapp.com/" + dir + fileName;
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
