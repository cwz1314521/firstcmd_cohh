package com.hema.newretail.backstage.controller.memberManager;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.memberManager.GiveCouponCondition;
import com.hema.newretail.backstage.common.queryparam.memberManager.UserManagementCondition;
import com.hema.newretail.backstage.common.queryparam.memberManager.UserManagementDetailCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.controller.BaseController;
import com.hema.newretail.backstage.entry.BaseUserInfoEntry;
import com.hema.newretail.backstage.entry.BusiCouponGiveAuditing;
import com.hema.newretail.backstage.service.memberManager.UserManagementDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.memberManager
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-15 11:15
 */
@Api(description = "用户管理")
@RestController
@RequestMapping(value = "/member")
@AutoLog
public class UserManagementController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private UserManagementDataService userManagementDataService;

    @ApiOperation("查询用户")
    @PostMapping(value = "/list")
    public Response list(@RequestBody UserManagementCondition condition) {
        return userManagementDataService.queryList(condition);
    }

    /**
     * 批量冻结
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量冻结")
    @PostMapping(value = "/batchFreeze")
    public Response userFreezeList(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return Response.failure("id不允许为空！");
        }
        return userManagementDataService.batchFreeze(ids);
    }

    /**
     * 批量解冻
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量解冻")
    @PostMapping(value = "/batchRelease")
    public Response userThawList(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return Response.failure("id不允许为空！");
        }
        return userManagementDataService.batchRelease(ids);
    }

    @ApiOperation(value = "导出会员信息Excel", produces = "application/octet-stream")
    @GetMapping(value = "/excel")
    @ResponseBody
    public Response excel(UserManagementCondition userManaCondition, HttpServletResponse response) {
        return userManagementDataService.excel(userManaCondition, response);
    }

    @ApiOperation("查询用户优惠券信息")
    @PostMapping(value = "/couponList")
    public Response couponList(@RequestBody UserManagementDetailCondition condition) {
        return userManagementDataService.queryCouponList(condition);
    }

    @ApiOperation("赠券")
    @PostMapping(value = "/giveCoupon")
    public Response giveCoupon(@RequestBody BusiCouponGiveAuditing condition) {
        BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
        if (null == baseUserInfoEntry || null == baseUserInfoEntry.getId()) {
            return Response.failure("获取登录信息失败，请重新登录。");
        }
        if (null == condition) {
            return Response.failure("参数不能为空");
        }
        if (null == condition.getCouponAuditingId()) {
            return Response.failure("优惠券不允许为空");
        }
        if (null == condition.getCouponGiveNumber()) {
            return Response.failure("赠送张数不允许为空");
        }
        if (0 == condition.getCouponGiveNumber()) {
            return Response.failure("赠送张数不应该小于或等于零");
        }
        if (StringUtils.isEmpty(condition.getGivePersonId())) {
            return Response.failure("赠送人不允许为空");
        }
        condition.setApplicant(baseUserInfoEntry.getRealName());
        condition.setApplicantId(baseUserInfoEntry.getId());
        return userManagementDataService.addGiveCoupon(condition);
    }
}
