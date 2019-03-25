package com.hema.newretail.backstage.service.memberManager;

import com.hema.newretail.backstage.common.queryparam.memberManager.UserManagementCondition;
import com.hema.newretail.backstage.common.queryparam.memberManager.UserManagementDetailCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BusiCouponGiveAuditing;

import javax.servlet.http.HttpServletResponse;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.memberManager
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-15 11:18
 */
public interface UserManagementDataService {

    /**
     * 查询用户信息
     *
     * @param condition
     * @return
     */
    Response queryList(UserManagementCondition condition);

    /**
     * 批量冻结
     *
     * @param ids
     * @return
     */
    Response batchFreeze(String ids);

    /**
     * 批量解冻
     *
     * @param ids
     * @return
     */
    Response batchRelease(String ids);

    /**
     * 导出excel
     *
     * @param condition
     * @param response
     * @return
     */
    Response excel(UserManagementCondition condition, HttpServletResponse response);

    /**
     * 查询用户的优惠券信息
     *
     * @param condition
     * @return
     */
    Response queryCouponList(UserManagementDetailCondition condition);

    /**
     * 赠券
     *
     * @param vo
     * @return
     */
    Response addGiveCoupon(BusiCouponGiveAuditing vo);
}
