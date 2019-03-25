package com.hema.newretail.backstage.controller.data;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.queryparam.data.DataSalesStructureHistogramCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.service.data.DataStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.controller.data
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-18 13:41
 */
@Api(description = "运营数据统计-饮品销量结构统计")
@RestController
@RequestMapping("/statics/salesStructure")
@AutoLog
public class DataStatisticsController {

    @Autowired
    private DataStatisticsService dataStatisticsService;

    @ApiOperation("查看饮品销量统计柱状图")
    @PostMapping("/histogram")
    public Response histogram(@RequestBody DataSalesStructureHistogramCondition vo) {
        try {
            return dataStatisticsService.queryHistogram(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    @ApiOperation("查询饮品制杯统计列表")
    @PostMapping("/list")
    public Response list(@RequestBody DataSalesStructureHistogramCondition vo) {
        try {
            return dataStatisticsService.queryList(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }

    @ApiOperation("饮品制杯统计列表导出")
    @GetMapping("/excel")
    public Response excel(DataSalesStructureHistogramCondition vo, HttpServletResponse response) {
        try {
            return dataStatisticsService.exportExcel(vo, response);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failure();
        }
    }
}
