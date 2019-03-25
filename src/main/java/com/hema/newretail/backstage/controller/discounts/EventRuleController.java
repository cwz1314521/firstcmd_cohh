package com.hema.newretail.backstage.controller.discounts;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.discounts.EventRuleService;
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

import javax.servlet.http.HttpServletRequest;

/**
 * @Department 新零售
 * @ClassName EventController
 * @Description 活动规则
 * @Author ---CWZ
 * @Date 2019/2/28 11:11
 * @Version 1.0
 **/

@Api(description = "活动规则管理")
@Controller
@RequestMapping("/eventRule")
@AutoLog
public class EventRuleController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private EventRuleService eventRuleService;


    /**
     *
     * 功能描述: list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询活动规则列表")
    @ResponseBody
    public Response eventRuleList(@RequestBody @Validated EventRuleListCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventRuleService.eventRuleList(condition);
        }
    }

    /**
     *
     * 功能描述: add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    @PostMapping(value = "/add")
    @ApiOperation("添加活动规则")
    @ResponseBody
    public Response eventRuleAdd(HttpServletRequest request, @RequestBody @Validated EventAddRuleCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventRuleService.eventRuleAdd(request,condition);
        }
    }

    /**
     *
     * 功能描述: edit
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    @PostMapping(value = "/edit")
    @ApiOperation("编辑活动规则")
    @ResponseBody
    public Response eventRuleEdit(@RequestBody @Validated EventEditRuleCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventRuleService.eventRuleEdit(condition);
        }
    }

    /**
     *
     * 功能描述: delete
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除活动规则")
    @ResponseBody
    public Response eventRuleDelete(@RequestBody @Validated EventDeleteRuleCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventRuleService.eventRuleDelete(condition);
        }
    }

    /**
     * 功能描述: 查询当前占用饮品和设备
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    @PostMapping(value = "/menuOrMachine")
    @ApiOperation("查询当前占用饮品和设备")
    @ResponseBody
    public Response menuOrMachine(@RequestBody @Validated MenuOrMachineCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventRuleService.menuOrMachine(condition);
        }
    }
}
