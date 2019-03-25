package com.hema.newretail.backstage.controller.data;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.data.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.service.data.DataSalesService;
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
 * @ClassName DataSalesController
 * @Description 数据统计销量相关
 * @Author ---CWZ
 * @Date 2018/12/17 15:03
 * @Version 1.0
 **/


@Api(description = "数据统计销量")
@Controller
@RequestMapping("/machine/data")
@AutoLog
public class DataSalesController {



    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    @Autowired
    private DataSalesService dataSalesService;
    private static final String ONES = "1";
    private static final String TWOS = "2";
    private static final String SDF = "yyyy-MM-dd";
    private final static String EMPTY ="";
    /**
     *
     * 功能描述: 数据统计销量趋势接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @PostMapping(value = "/trend")
    @ApiOperation("数据统计销量趋势")
    @ResponseBody
    public Response trend(@RequestBody @Validated DataSalesTrendCondition condition, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            if(ONES.equals(condition.getType())){
                if(condition.getDateStart() != null && !EMPTY.equals(condition.getDateStart())){
                    if(condition.getDateEnd() != null && !EMPTY.equals(condition.getDateEnd())){
                        long time = TimeUtil.stringToDate(condition.getDateEnd(), SDF).getTime() - TimeUtil.stringToDate(condition.getDateStart(), SDF).getTime();
                        Long lo = 1000L*60L*60L*24L*60L;
                        if(time < 0){
                            return Response.failure("结束日期不可以先于开始日期");
                        }else if(time > lo){
                            return Response.failure("日期过长请按月查询");
                        }else {
                            return dataSalesService.trendDay(condition);
                        }
                    }
                }

            }
            if(TWOS.equals(condition.getType())){
                if(condition.getDateStart() != null && !EMPTY.equals(condition.getDateStart())){
                    if(condition.getDateEnd() != null && !EMPTY.equals(condition.getDateEnd())){
                        long times = (TimeUtil.stringToDate(condition.getDateEnd()+"-01", SDF).getTime() - TimeUtil.stringToDate(condition.getDateStart()+"-01", SDF).getTime());
                        Long loo = 1000L*60L*60L*24L*30L*24L;
                        if(times < 0){
                            return Response.failure("结束月份不可以先于开始月份");
                        }else if(times > loo){
                            return Response.failure("日期过长");
                        }else {
                            return dataSalesService.trendMonth(condition);
                        }
                    }
                }
            }
            return Response.failure("参数有误");
        }
    }

    /**
     *
     * 功能描述: 数据统计销量列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @PostMapping(value = "/list")
    @ApiOperation("数据统计销量列表")
    @ResponseBody
    public Response list(@RequestBody @Validated DataSalesListCondition condition, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return dataSalesService.list(condition);
        }
    }

    /**
     *
     * 功能描述: 数据统计销量列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @GetMapping(value = "/excel")
    @ApiOperation("数据统计销量列表导出")
    @ResponseBody
    public Response excel(DataSalesListCondition condition,HttpServletResponse response, BindingResult bindingResult) throws Exception{

            return dataSalesService.excel(condition ,response);
    }


    /**
     *
     * 功能描述: 数据出杯统计列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @PostMapping(value = "/cup")
    @ApiOperation("数据统计出杯数列表")
    @ResponseBody
    public Response cup(@RequestBody @Validated CupListCondition condition, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return dataSalesService.cup(condition);
        }
    }
    /**
     *
     * 功能描述: 数据出杯统计列表导出接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @GetMapping (value = "/cupExcel")
    @ApiOperation("数据统计出杯数导出")
    @ResponseBody
    public Response cupExcel(CupListCondition condition, HttpServletResponse response, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return dataSalesService.cupExcel(condition,response);
        }
    }

    /**
     *
     * 功能描述: 数据出杯统计详细接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    @PostMapping(value = "/cupDetail")
    @ApiOperation("数据统计出杯详情")
    @ResponseBody
    public Response cupDetail(@RequestBody @Validated CupDetailCondition condition, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            logger.error("数据校验没通过......" + bindingResult.getFieldError().getDefaultMessage());
            return Response.failure(bindingResult.getFieldError().getDefaultMessage());
        } else {
            return dataSalesService.cupDetail(condition);
        }
    }
}
