package com.hema.newretail.backstage.controller.data;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.data.DrinkDetailCondition;
import com.hema.newretail.backstage.common.queryparam.data.DrinkSalesListCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.data.DrinkSalesService;
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
 * @ClassName DrinkSalesController
 * @Description 设备饮品销量明细
 * @Author ---yingyan
 * @Date 2019/3/16
 * @Version 1.0
 **/


@Api(description = "设备饮品销量明细")
@Controller
@RequestMapping("/machine/data")
@AutoLog
public class DrinkSalesController {



    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    @Autowired
    private DrinkSalesService drinkSalesService;

    /**
     *
     * 功能描述: 设备饮品销售列表接口
     *
     * @param  condition
     * @return  Response
     * @author  yingyan
     * @date  2019/3/16
     */
    @PostMapping(value = "/drinkSales")
    @ApiOperation("设备饮品销售列表")
    @ResponseBody
    public Response drink(@RequestBody @Validated DrinkSalesListCondition condition, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return drinkSalesService.drink(condition);
        }
    }

    /**
     *
     * 功能描述: 设备饮品销售明细接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @PostMapping(value = "/drinkDetail")
    @ApiOperation("设备饮品销售明细")
    @ResponseBody
    public Response drinkDetail(@RequestBody @Validated DrinkDetailCondition condition, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return drinkSalesService.drinkDetail(condition);
        }
    }

    /**
     *
     * 功能描述: 设备饮品销售列表导出接口
     *
     * @param  condition
     * @return  Response
     * @author  yingyan
     * @date  2019/3/18
     */
    @GetMapping(value = "/drinkExcel")
    @ApiOperation("设备饮品销售列表导出")
    @ResponseBody
    public Response drinkExcel(DrinkSalesListCondition condition, HttpServletResponse response, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return drinkSalesService.drinkExcel(condition,response);
        }
    }
}
