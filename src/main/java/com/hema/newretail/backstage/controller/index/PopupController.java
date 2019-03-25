package com.hema.newretail.backstage.controller.index;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.diy.IdCondition;
import com.hema.newretail.backstage.common.queryparam.index.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.index.PopupService;
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
 * @ClassName popupController
 * @Description 首页弹窗管理controller
 * @Author ---CWZ
 * @Date 2019/03/11 13:11
 * @Version 1.0
 **/

@Api(description = "≡(▔﹏▔)≡首页弹窗管理")
@Controller
@RequestMapping("/popup")
public class PopupController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private PopupService popupService;


    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    @PostMapping(value = "/list")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告列表")
    @ResponseBody
    public Response list(@RequestBody @Validated PopupListCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return popupService.list(condition);
        }
    }

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    @PostMapping(value = "/add")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告add")
    @ResponseBody
    public Response add(@RequestBody @Validated PopupAddCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return popupService.add(condition);
        }
    }

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    @PostMapping(value = "/edit")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告edit")
    @ResponseBody
    public Response edit(@RequestBody @Validated PopupEditCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return popupService.edit(condition);
        }
    }

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    @PostMapping(value = "/delete")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告edit")
    @ResponseBody
    public Response delete(@RequestBody @Validated IdCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return popupService.delete(condition);
        }
    }
}
