package com.hema.newretail.backstage.common.ueditor.upload;

import com.hema.newretail.backstage.common.caches.ApplicationContextUtil;
import com.hema.newretail.backstage.common.ueditor.define.AppInfo;
import com.hema.newretail.backstage.common.ueditor.define.BaseState;
import com.hema.newretail.backstage.common.ueditor.define.FileType;
import com.hema.newretail.backstage.common.ueditor.define.State;
import com.hema.newretail.backstage.common.utils.ossutil.AliyunOSSClientUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BinaryUploader {

//    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
//        FileItemStream fileStream = null;
//        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
//
//        if (!ServletFileUpload.isMultipartContent(request)) {
//            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
//        }
//
//        ServletFileUpload upload = new ServletFileUpload(
//                new DiskFileItemFactory());
//
//        if (isAjaxUpload) {
//            upload.setHeaderEncoding("UTF-8");
//        }
//
//        try {
//            FileItemIterator iterator = upload.getItemIterator(request);
//
//            while (iterator.hasNext()) {
//                fileStream = iterator.next();
//
//                if (!fileStream.isFormField()) {
//                    break;
//                }
//                fileStream = null;
//            }
//
//            if (fileStream == null) {
//                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
//            }
//
//            String savePath = (String) conf.get("savePath");
//            String originFileName = fileStream.getName();
//            String suffix = FileType.getSuffixByFilename(originFileName);
//
//            originFileName = originFileName.substring(0,
//                    originFileName.length() - suffix.length());
//            savePath = savePath + suffix;
//
//            long maxSize = ((Long) conf.get("maxSize")).longValue();
//
//            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
//                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
//            }
//
//            savePath = PathFormat.parse(savePath, originFileName);
//
//            String physicalPath = (String) conf.get("rootPath") + savePath;
//
//            InputStream is = fileStream.openStream();
//            State storageState = StorageManager.saveFileByInputStream(is,
//                    physicalPath, maxSize);
//            is.close();
//
//            if (storageState.isSuccess()) {
//                storageState.putInfo("url", PathFormat.format(savePath));
//                storageState.putInfo("type", suffix);
//                storageState.putInfo("original", originFileName + suffix);
//            }
//
//            return storageState;
//        } catch (FileUploadException e) {
//            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
//        } catch (IOException e) {
//        }
//        return new BaseState(false, AppInfo.IO_ERROR);
//    }

    public final State save(HttpServletRequest request, Map<String, Object> conf) {

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());

            String originFileName = multipartFile.getOriginalFilename();
            String suffix = FileType.getSuffixByFilename(originFileName);
            long fileSize = multipartFile.getSize();
            String newFileName = System.currentTimeMillis() + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();
            if (fileSize > maxSize) {
                return new BaseState(false, AppInfo.MAX_SIZE);
            }

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }
            //自定义
            State storageState = new BaseState(true);
            AliyunOSSClientUtil aliyunOSSClientUtil = ApplicationContextUtil.getBean("aliyunOSSClientUtil");
            String filePath = aliyunOSSClientUtil.uploadObject2OSS(multipartFile, newFileName);
            if (null != filePath && filePath.length() > 0) {
                storageState.putInfo("size", fileSize);
                storageState.putInfo("title", originFileName);
            } else {
                storageState = new BaseState(false, AppInfo.IO_ERROR);
                return storageState;
            }
            if (storageState.isSuccess()) {
                storageState.putInfo("url", filePath);
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", multipartFile.getOriginalFilename());
            }
            return storageState;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);
        return list.contains(type);
    }
}
