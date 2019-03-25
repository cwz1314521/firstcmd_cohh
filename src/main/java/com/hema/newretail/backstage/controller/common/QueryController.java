package com.hema.newretail.backstage.controller.common;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.common.CompanyNameCondition;
import com.hema.newretail.backstage.common.queryparam.common.MapGeohashTagCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.common.QueryService;
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
 * @ClassName QueryController
 * @Description 预查询公共接口controller
 * @Author ---CWZ
 * @Date 2018/12/11 9:56
 * @Version 1.0
 **/

@Api(description = "预查询")
@Controller
@RequestMapping(value = "/global")
@AutoLog
public class QueryController {


    @Autowired
    private QueryService queryService;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 功能描述: 代理公司实时走索
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @PostMapping(value = "/agentCompany")
    @ApiOperation("代理列表")
    @ResponseBody
    public Response agent(@RequestBody @Validated CompanyNameCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return queryService.agent(condition);
        }
    }

    /**
     * 功能描述: 分公司实时搜索
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @PostMapping(value = "/company")
    @ApiOperation("分公司列表")
    @ResponseBody
    public Response company(@RequestBody @Validated CompanyNameCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return queryService.company(condition);
        }
    }

    /**
     * 功能描述: 分公司实时搜索
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @PostMapping(value = "/gridCompany")
    @ApiOperation("网格公司列表")
    @ResponseBody
    public Response grid(@RequestBody @Validated CompanyNameCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return queryService.grid(condition);
        }
    }

    /**
     * 功能描述: 分公司实时搜索
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @PostMapping(value = "/AllMachine")
    @ApiOperation("机器列表")
    @ResponseBody
    public Response AllMachine(@RequestBody @Validated CompanyNameCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return queryService.allMachine(condition);
        }
    }

    /**
     * 功能描述: 片区实时搜索
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @PostMapping(value = "/AllZone")
    @ApiOperation("片区实时搜索")
    @ResponseBody
    public Response AllZone(@RequestBody @Validated CompanyNameCondition condition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return queryService.allZone(condition);
        }
    }

    @ApiOperation("查询所有饮品分类")
    @PostMapping("/allClassify")
    @ResponseBody
    public Response allClassify() {
        try {
            return queryService.queryAllClassify();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    /**
     * 功能描述:
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    @ApiOperation("查询所有优惠券")
    @PostMapping("/allDiscounts")
    @ResponseBody
    public Response allDiscounts() {
        return queryService.allDiscounts();
    }

    /**
     * 功能描述:
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    @ApiOperation("查询所有饮品")
    @PostMapping("/allMenu")
    @ResponseBody
    public Response allMenu() {
        return queryService.allMenu();
    }

    /**
     * 功能描述:
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    @ApiOperation("查询所有组织架构")
    @PostMapping("/allOrganization")
    @ResponseBody
    public Response allOrganization() {
        return queryService.allOrganization();
    }


    /**
     * 功能描述:
     *
     * @param
     * @return
     * @author cwz
     * @date 2019/2/26 15:44
     */
    @ApiOperation("查询所有文章类型")
    @PostMapping("/allArticleType")
    @ResponseBody
    public Response allArticleType() {
        return queryService.allArticleType();
    }

    @ApiOperation("查询地图可视范围内的所有设备经纬度坐标")
    @PostMapping("/allHashcodeTag")
    @ResponseBody
    public Response allHashcodeTag(@RequestBody MapGeohashTagCondition condition) {
        return queryService.queryAllTag(condition);
    }

    @ApiOperation("查询所有活动规则")
    @PostMapping("/allActivityRule")
    @ResponseBody
    public Response allActivityRule() {
        return queryService.queryAllActivityRule();
    }
}
