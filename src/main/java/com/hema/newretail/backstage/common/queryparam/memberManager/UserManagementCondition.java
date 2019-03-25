package com.hema.newretail.backstage.common.queryparam.memberManager;

import lombok.Data;

/**
 * @author zhs
 */
@Data
public class UserManagementCondition {

    private Integer pageNum;

    private Integer pageSize;

    /**
     * 条件查询
     */
    private String nickname;

    /**
     * 起始日期
     */
    private String preRegistrationDate;

    /**
     * 终止日期
     */
    private String registrationDate;

    /**
     * 状态  0正常/1冻结
     */
    private String status;

    /**
     * 0小程序，1安卓，2iOS
     */
    private Integer registerSource;

}
