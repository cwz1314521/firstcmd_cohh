package com.hema.newretail.backstage.controller.financial;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.financial.MachineCashListCondition;
import com.hema.newretail.backstage.common.queryparam.financial.RetiredCashCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.financial.MachineCashService;
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

/**
 * @Department 新零售
 * @ClassName MachineCashController
 * @Description 设备押金管理
 * @Author ---CWZ
 * @Date 2019/1/7 17:02
 * @Version 1.0
 **/

@Api(description = "≡(▔﹏▔)≡设备押金管理")
@Controller
@RequestMapping("/machineCash")
public class MachineCashController {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    @Autowired
    private MachineCashService machineCashService;

    /**
     *
     * 功能描述:设备押金管理列表
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @PostMapping(value = "/list")
    @ApiOperation("≡(▔﹏▔)≡设备押金管理列表")
    @ResponseBody
    public Response list(@RequestBody @Validated MachineCashListCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return machineCashService.list(condition);
        }
    }

    /**
     *
     * 功能描述:设备押金管理列表导出（get请求  表单提交）
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @GetMapping (value = "/excel")
    @ApiOperation("≡(▔﹏▔)≡设备押金管理列表导出（get请求  表单提交）")
    @ResponseBody
    public Response excel(HttpServletResponse response, MachineCashListCondition condition)throws Exception{
            return machineCashService.excel(response,condition);
    }

    /**
     *
     * 功能描述:批量设置补贴规则
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @PostMapping(value = "/batchRule")
    @ApiOperation("≡(▔﹏▔)≡批量设置补贴规则")
    @ResponseBody
    public Response batchRule(HttpServletRequest request, @RequestBody @Validated RetiredCashCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return machineCashService.batchRule(request,condition);
        }
    }

    /**
     *
     * 功能描述:设置补贴规则
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @PostMapping(value = "/rule")
    @ApiOperation("≡(▔﹏▔)≡设置补贴规则")
    @ResponseBody
    public Response rule(HttpServletRequest request,@RequestBody @Validated RetiredCashCondition condition,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return machineCashService.rule(request,condition);
        }
    }

    /**
     *
     * 功能描述:设置违约金
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @PostMapping(value = "/penalSum")
    @ApiOperation("≡(▔﹏▔)≡设置违约金")
    @ResponseBody
    public Response penalSum(HttpServletRequest request, @RequestBody @Validated RetiredCashCondition condition,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return machineCashService.penalSum(request,condition);
        }
    }

    /**
     *
     * 功能描述:停止补贴
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @PostMapping(value = "/stopCash")
    @ApiOperation("≡(▔﹏▔)≡停止补贴")
    @ResponseBody
    public Response stopCash(HttpServletRequest request, @RequestBody @Validated RetiredCashCondition condition,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return machineCashService.stopCash(request,condition);
        }
    }

    /**
     *
     * 功能描述:停止分润
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    @PostMapping(value = "/stopSeeSplitting")
    @ApiOperation("≡(▔﹏▔)≡停止分润")
    @ResponseBody
    public Response stopSeeSplitting(HttpServletRequest request, @RequestBody @Validated RetiredCashCondition condition,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return machineCashService.stopSeeSplitting(request,condition);
        }
    }
}
