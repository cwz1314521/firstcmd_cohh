package com.hema.newretail.backstage.model.memberManager;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.memberManager
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-15 15:20
 */
@Data
public class UserListBo implements Serializable {
    private static final long serialVersionUID = 7499526753352731040L;

    private String id;
    /**
     * 小程序微信昵称与头像
     */
    private String nickname;
    private String headPortrait;
    /**
     * APP的用户昵称与头像
     */
    private String appNickName;
    private String appHeadPortrait;
    private String phoneNumber;
    /**
     * 注册来源 0小程序，1安卓，2iOS
     */
    private Integer registerSource;
    /**
     * 手机设备号
     */
    private String phoneDeviceNumber;
    /**
     * 状态 0正常1冻结
     */
    private String status;

    /**
     * 注册时间
     */
    private Date registrationDate;

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


    /**
     * 优惠券数量
     */
    private Integer couponNum;
    /**
     * 配方数量
     */
    private Integer boxGroupNum;

    /**
     * 关注数量
     */
    private Integer concernNum;
    /**
     * 消费次数
     */
    private Integer consumeNum;

    /**
     * 饮品卡
     */
    private Integer menuCardNum;

    /**
     * 邀请人
     */
    private String shareOpenName;
}
