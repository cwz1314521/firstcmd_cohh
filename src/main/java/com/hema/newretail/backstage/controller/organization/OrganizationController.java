package com.hema.newretail.backstage.controller.organization;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.organization.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.organization.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Department 新零售
 * @ClassName OrganizationController
 * @Description 组织架构
 * @Author ---CWZ
 * @Date 2019/2/15 15:14
 * @Version 1.0
 **/

@Api(description = "组织架构")
@RestController
@RequestMapping(value = "/organization")
@AutoLog
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);


    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    @PostMapping(value = "/list")
    @ApiOperation("组织架构列表")
    @ResponseBody
    public Response list() {
            return organizationService.list();
    }

    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    @PostMapping(value = "/add")
    @ApiOperation("组织架构新增")
    @ResponseBody
    public Response add(@RequestBody @Validated OrganizationAddCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return organizationService.add(condition);
        }
    }

    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    @PostMapping(value = "/edit")
    @ApiOperation("组织架构修改")
    @ResponseBody
    public Response edit(@RequestBody @Validated OrganizationEditCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return organizationService.edit(condition);
        }
    }

    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    @PostMapping(value = "/delete")
    @ApiOperation("组织架构删除")
    @ResponseBody
    public Response delete(@RequestBody @Validated OrganizationDeleteCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return organizationService.delete(condition);
        }
    }
}
