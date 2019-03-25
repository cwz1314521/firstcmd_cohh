package com.hema.newretail.backstage.controller.financial;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.financial.AgentCapitalCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.financial.AgentCapitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName AgentCapitalController
 * @Description 代理商账户资金表相关接口
 * @Author ---CWZ
 * @Date 2019/1/9 14:41
 * @Version 1.0
 **/


@Api(description = "≡(▔﹏▔)≡代理商账户资金表相关接口")
@Controller
@RequestMapping("/agentCapital")
public class AgentCapitalController {


    @Autowired
    private AgentCapitalService agentCapitalService;

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);


    /**
     *
     * 功能描述:  list
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     */
    @PostMapping(value = "/list")
    @ApiOperation("≡(▔﹏▔)≡代理商账户资金列表")
    @ResponseBody
    public Response list(@RequestBody @Validated AgentCapitalCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return agentCapitalService.list(condition);
        }
    }

    /**
     *
     * 功能描述:  excel
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     */
    @GetMapping(value = "/excel")
    @ApiOperation("≡(▔﹏▔)≡代理商账户资金excel")
    @ResponseBody
    public Response excel(HttpServletResponse response,AgentCapitalCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return agentCapitalService.excel(response,condition);
        }
    }


    /**
     *
     * 功能描述:清算
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     */
    @PostMapping(value = "/clear")
    @ApiOperation("≡(▔﹏▔)≡代理商账户资金清算")
    @ResponseBody
    public Response clearing(@RequestBody @Validated AgentCapitalCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return agentCapitalService.clearing(condition);
        }
    }

    /**
     *
     * 功能描述:修改信息
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     */
    @PostMapping(value = "/edit")
    @ApiOperation("≡(▔﹏▔)≡代理商账户资金修改")
    @ResponseBody
    public Response edit(@RequestBody @Validated AgentCapitalCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return agentCapitalService.edit(condition);
        }
    }


}
