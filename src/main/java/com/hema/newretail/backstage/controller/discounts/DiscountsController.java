package com.hema.newretail.backstage.controller.discounts;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.discounts.DiscountsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Department 新零售
 * @ClassName DiscountsController
 * @Description 优惠券
 * @Author ---CWZ
 * @Date 2019/1/3 17:11
 * @Version 1.0
 **/

@Api(description = "优惠券管理")
@Controller
@RequestMapping("/discounts")
@AutoLog
public class DiscountsController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private DiscountsService discountsService;

    /**
     *
     * 功能描述: list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询优惠券列表")
    @ResponseBody
    public Response discountsList(@RequestBody @Validated DiscountsListCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return discountsService.discountsList(condition);
        }
    }

    /**
     *
     * 功能描述: add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    @PostMapping(value = "/add")
    @ApiOperation("添加优惠券")
    @ResponseBody
    public Response discountsAdd(@RequestBody @Validated DiscountsAddCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return discountsService.discountsAdd(condition);
        }
    }

    /**
     *
     * 功能描述: info
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    @PostMapping(value = "/info")
    @ApiOperation("查看优惠券详情")
    @ResponseBody
    public Response discountsInfo(@RequestBody @Validated DiscountsInfoCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return discountsService.discountsInfo(condition);
        }
    }

    /**
     *
     * 功能描述: edit
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    @PostMapping(value = "/edit")
    @ApiOperation("编辑优惠券")
    @ResponseBody
    public Response discountsEdit(@RequestBody @Validated DiscountsEditCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return discountsService.discountsEdit(condition);
        }
    }

    /**
     *
     * 功能描述: check
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    @PostMapping(value = "/check")
    @ApiOperation("审核优惠券")
    @ResponseBody
    public Response discountsCheck(@RequestBody @Validated EventCheckCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return discountsService.discountsCheck(condition);
        }
    }

    /**
     *
     * 功能描述: info
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除优惠券")
    @ResponseBody
    public Response discountsDelete(@RequestBody @Validated DiscountsInfoCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return discountsService.discountsDelete(condition);
        }
    }
}
