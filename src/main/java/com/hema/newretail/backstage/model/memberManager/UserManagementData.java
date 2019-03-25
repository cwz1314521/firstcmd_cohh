package com.hema.newretail.backstage.model.memberManager;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * @author jiahao
 */
@Data
public class UserManagementData {

    /**
     * 用户主键id
     */
    private String id;
    private String openId;
    private String publicOpenId;
    private String unionID;
    private String shareOpenId;
    private String userQRCode;
    private String token;
    /**
     * 性别默认1男，0女，年龄
     */
    private Integer gender;
    private Integer age;

    /**
     * 0用户未使用APP，1已经使用了APP
     */
    private Integer appIsUse;
    /**
     * APP的用户昵称与头像
     */
    private String appNickName;
    private String appHeadPortrait;

    private String phoneNumber;

    /**
     * 小程序微信昵称与头像
     */
    private String nickname;
    private String headPortrait;

    /**
     * 注册来源
     * 0小程序，1安卓，2iOS
     * 手机设备号
     * 0解冻1冻结
     * 注册时间
     */
    private Integer registerSource;
    private String phoneDeviceNumber;
    private String status;
    private Date registrationDate;

    private String province;

    private String city;

    private String area;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;

    /**
     * 最后一次登录使用的设备，比如 IOS，Android
     */
    private String loginUsedDevice;

}
