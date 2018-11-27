package com.hema.newretail.backstage.controller.erp;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreListCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreNumCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStorePreAllCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.erp.OutStoreService;
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
 * @ClassName OutStoreController
 * @Description 分公司  --  出库
 * @Author ---CWZ
 * @Date 2018/11/27 14:01
 * @Version 1.0
 **/
@Api(description =  "≡(▔﹏▔)≡分后台--出库--相关接口")
@Controller
@RequestMapping("/outstore")
public class OutStoreController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private OutStoreService outStoreService;
    /**
     *
     * 功能描述: 分后台---待出库--列表
     *
     * @param:
     * @return:
     * @author: admin
     * @date: 2018/11/27 11:43
     */
    @ApiOperation("≡(▔﹏▔)≡ 分后台---待出库--详单列表")
    @PostMapping("/inStoreLoadList")
    @ResponseBody
    public Response inStoreLoadList(){
        return outStoreService.inStoreLoadList();
    }
    /**
     *
     * 功能描述: 分后台---待出库--列表
     *
     * @param:
     * @return:
     * @author: admin
     * @date: 2018/11/27 11:43
     */
    @ApiOperation("≡(▔﹏▔)≡ 分后台---待出库--列表")
    @PostMapping("/waitOutstoreList")
    @ResponseBody
    public Response waitOutstoreList(@RequestBody @Validated InStoreListCondition inStoreListCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return outStoreService.waitOutstoreList(inStoreListCondition);
        }
    }

    /**
     *
     * 功能描述:分后台  列表
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @ApiOperation("≡(▔﹏▔)≡ 分后台--今日出库 -列表")
    @PostMapping("/todayOutstore")
    @ResponseBody
    public Response todayOutstore(){

            return outStoreService.todayOutstore();
    }

    /**
     *
     * 功能描述: 分后台--扫码出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @ApiOperation("≡(▔﹏▔)≡ 分后台--扫码出库")
    @PostMapping("/outstorePre")
    @ResponseBody
    public Response outstorePre(@RequestBody @Validated InStorePreAllCondition inStoreListCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return outStoreService.outstorePre(inStoreListCondition);
        }
    }

    /**
     *
     * 功能描述: 分后台--输码出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @ApiOperation("≡(▔﹏▔)≡ 分后台--输码出库")
    @PostMapping("/outstoreNum")
    @ResponseBody
    public Response outstoreNum(HttpServletRequest request, @RequestBody @Validated InStoreNumCondition inStoreNumCondition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......"+bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return outStoreService.outstoreNum(request,inStoreNumCondition);
        }
    }


    /**
     *
     * 功能描述: 分后台-- 确认提交出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    @ApiOperation("≡(▔﹏▔)≡ 分后台-- 确认提交出库")
    @PostMapping("/outstore")
    @ResponseBody
    public Response outstore(HttpServletRequest request){

        return outStoreService.outstore(request);
    }
}
