package com.hema.newretail.backstage.controller.discounts;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.discounts.EventService;
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

import javax.servlet.http.HttpSession;

/**
 * @Department 新零售
 * @ClassName EventController
 * @Description 活动
 * @Author ---CWZ
 * @Date 2019/1/3 17:11
 * @Version 1.0
 **/

@Api(description = "活动管理")
@Controller
@RequestMapping("/event")
@AutoLog
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private EventService eventService;


    /**
     * 功能描述: list
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/list")
    @ApiOperation("查询活动列表")
    @ResponseBody
    public Response eventList(@RequestBody @Validated EventListCondition condition, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventService.eventList(condition);
        }
    }

    /**
     * 功能描述: add
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/add")
    @ApiOperation("添加活动")
    @ResponseBody
    public Response eventAdd(@RequestBody @Validated EventAddCondition condition, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventService.eventAdd(condition);
        }
    }

    /**
     * 功能描述: info
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/info")
    @ApiOperation("查看活动信息")
    @ResponseBody
    public Response eventInfo(@RequestBody @Validated EventInfoCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventService.eventInfo(condition);
        }
    }

    /**
     * 功能描述: edit
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/edit")
    @ApiOperation("编辑活动")
    @ResponseBody
    public Response eventEdit(@RequestBody @Validated EventEditCondition condition, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventService.eventEdit(condition);
        }
    }

    /**
     * 功能描述: check
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/check")
    @ApiOperation("提交审核")
    @ResponseBody
    public Response eventCheck(@RequestBody @Validated EventCheckCondition condition, HttpSession session) {
        return eventService.eventCheck(condition, session);
    }

    /**
     * 功能描述: stop
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/stop")
    @ApiOperation("终止活动")
    @ResponseBody
    public Response eventStop(@RequestBody @Validated EventInfoCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventService.eventStop(condition);
        }
    }

    /**
     * 功能描述: delete
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除活动")
    @ResponseBody
    public Response eventDelete(@RequestBody @Validated EventInfoCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return eventService.eventDelete(condition);
        }
    }
}
