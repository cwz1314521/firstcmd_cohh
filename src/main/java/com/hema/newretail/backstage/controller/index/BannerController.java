package com.hema.newretail.backstage.controller.index;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.index.*;
import com.hema.newretail.backstage.common.queryparam.system.IdCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.index.BannerService;
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
 * @ClassName BannerController
 * @Description 首页图片controller
 * @Author ---CWZ
 * @Date 2018/12/24 13:11
 * @Version 1.0
 **/

@Api(description = "≡(▔﹏▔)≡首页banner相关")
@Controller
@RequestMapping("/banner")
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private BannerService systemManageService;


    /**
     *
     * 功能描述: 机器大屏广告列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     */
    @PostMapping(value = "/list")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告列表")
    @ResponseBody
    public Response machinePic(@RequestBody @Validated BannerListCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return systemManageService.machinePic(condition);
        }
    }

    /**
     *
     * 功能描述: 机器大屏广告add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     */
    @PostMapping(value = "/add")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告add")
    @ResponseBody
    public Response machinePicAdd(@RequestBody @Validated BannerAddCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return systemManageService.machinePicAdd(condition);
        }
    }

    /**
     *
     * 功能描述: 机器大屏广告edit
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     */
    @PostMapping(value = "/edit")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告edit")
    @ResponseBody
    public Response machinePicEdit(@RequestBody @Validated BannerEditCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return systemManageService.machinePicEdit(condition);
        }
    }

    /**
     *
     * 功能描述: 机器大屏广告delete
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     */
    @PostMapping(value = "/delete")
    @ApiOperation("≡(▔﹏▔)≡机器大屏广告edit")
    @ResponseBody
    public Response machinePicDelete(@RequestBody @Validated IdCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return systemManageService.machinePicDelete(condition);
        }
    }
}
