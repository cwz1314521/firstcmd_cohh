package com.hema.newretail.backstage.controller.device;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.device.outstore.CommonIdCondition;
import com.hema.newretail.backstage.common.queryparam.device.outstore.DeviceOutStoreAddCondition;
import com.hema.newretail.backstage.common.queryparam.device.outstore.DeviceOutStoreCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.controller.BaseController;
import com.hema.newretail.backstage.entry.BaseUserInfoEntry;
import com.hema.newretail.backstage.service.device.DeviceOutStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Department 新零售
 * @ClassName DeviceOutStoreController
 * @Description 设备管理--出库--Controller
 * @Author ---CWZ
 * @Date 2018/10/11 13:18
 * @Version 1.0
 **/

@Api(description = "设备管理-出库管理")
@Controller
@RequestMapping("/machineOutstock")
@AutoLog
public class DeviceOutStoreController extends BaseController {


    @Autowired
    private DeviceOutStoreService deviceManagementService;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);


    /**
     * 功能描述: 设备出库列表接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询设备出库列表")
    @ResponseBody
    public Response list(@RequestBody @Validated DeviceOutStoreCondition condition, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
            if (null == baseUserInfoEntry || null == baseUserInfoEntry.getOrgCode() || "".equals(baseUserInfoEntry.getOrgCode())) {
                return Response.failure("获取登录信息失败，请重新登录。");
            }
            condition.setOrgCode(baseUserInfoEntry.getOrgCode());
            return deviceManagementService.list(condition);
        }
    }

    /**
     * 功能描述: 设备出库列表接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @GetMapping(value = "/export")
    @ApiOperation("导出设备的出库信息")
    @ResponseBody
    public Response export(DeviceOutStoreCondition condition, HttpServletResponse response) throws Exception {
        return deviceManagementService.export(condition, response);
    }

    /**
     * 功能描述: 设备出库添加接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增出库单")
    @ResponseBody
    public Response add(HttpServletRequest request, @RequestBody @Valid DeviceOutStoreAddCondition condition, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            BaseUserInfoEntry baseUserInfoEntry = getUserInfo();
            if (null == baseUserInfoEntry || null == baseUserInfoEntry.getId()) {
                return Response.failure("获取登录信息失败，请重新登录。");
            }
            condition.setUserId(baseUserInfoEntry.getId());
            return deviceManagementService.add(request, condition);
        }
    }

    /**
     * 功能描述: 设备出库详情接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @PostMapping(value = "/info")
    @ApiOperation("查看设备出库详情")
    @ResponseBody
    public Response info(@RequestBody @Validated CommonIdCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return deviceManagementService.info(condition);
        }
    }

}