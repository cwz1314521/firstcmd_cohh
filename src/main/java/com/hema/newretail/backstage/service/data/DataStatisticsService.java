package com.hema.newretail.backstage.service.data;

import com.hema.newretail.backstage.common.queryparam.data.DataSalesStructureHistogramCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.data
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-18 14:39
 */
public interface DataStatisticsService {

    /**
     * 查询饮品销量柱状图
     *
     * @param vo vo
     * @return Response
     */
    Response queryHistogram(DataSalesStructureHistogramCondition vo);

    /**
     * 查询饮品制杯统计列表
     *
     * @param vo vo
     * @return Response
     */
    Response queryList(DataSalesStructureHistogramCondition vo);

    /**
     * 导出excel
     *
     * @param vo       vo
     * @param response response
     * @return Reponse
     * @throws Exception 异常
     */
    Response exportExcel(DataSalesStructureHistogramCondition vo, HttpServletResponse response) throws Exception;

}
