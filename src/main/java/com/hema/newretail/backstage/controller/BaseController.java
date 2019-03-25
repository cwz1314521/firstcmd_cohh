package com.hema.newretail.backstage.controller;

import com.alibaba.fastjson.JSON;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.UploadFileUtil;
import com.hema.newretail.backstage.common.utils.ossutil.AliyunOSSClientUtil;
import com.hema.newretail.backstage.entry.BaseUserInfoEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author zhs
 * @date 2018/8/27 17:43
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    public static String AUTH_ERROR_MSG = "获取用户登录信息失败，请重新登录系统。";

    @Autowired
    private HttpSession session;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AliyunOSSClientUtil aliyunOSSClientUtil;

    @ApiOperation("上传图片服务")
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(required = false) Integer proportionType, MultipartFile file) {
        return UploadFileUtil.uploadImageOss(file, proportionType);
    }

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Response upload(MultipartFile file) {
        String url = aliyunOSSClientUtil.uploadFileToOSS(file);
        if (StringUtils.isEmpty(url)) {
            return Response.failure("上传文件失败！");
        }
        return Response.success(url);
    }

    /**
     * 获取当前登录用户的用户信息
     *
     * @return
     */
    public BaseUserInfoEntry getUserInfo() {
        String userInfoJson = redisUtils.hget(AuthConstants.SESSION + session.getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (StringUtils.isEmpty(userInfoJson)) {
            throw new RuntimeException(AUTH_ERROR_MSG);
        }
        BaseUserInfoEntry baseUserInfoEntry = JSON.parseObject(userInfoJson, BaseUserInfoEntry.class);
        if (null == baseUserInfoEntry || null == baseUserInfoEntry.getPostId() || null == baseUserInfoEntry.getId()) {
            throw new RuntimeException(AUTH_ERROR_MSG);
        }
        return baseUserInfoEntry;
    }

    /**
     * 获取当前登录用户的userid
     *
     * @return
     */
    public Long getUserId() {
        BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
        return baseUserInfoEntry.getId();
    }

    /**
     * 获取当前登录用户的登陆名称
     *
     * @return
     */
    public String getUserName() {
        BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
        return StringUtils.isEmpty(baseUserInfoEntry.getUserName()) ? "" : baseUserInfoEntry.getUserName();
    }

    /**
     * 获取当前登录用户的真实姓名
     *
     * @return
     */
    public String getUserRealName() {
        BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
        return StringUtils.isEmpty(baseUserInfoEntry.getRealName()) ? "" : baseUserInfoEntry.getRealName();
    }

    /**
     * 获取当前登录用户的组织机构编码
     *
     * @return
     */
    public String getUserOrgCode() {
        BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
        return StringUtils.isEmpty(baseUserInfoEntry.getOrgCode()) ? "" : baseUserInfoEntry.getOrgCode();
    }
}
