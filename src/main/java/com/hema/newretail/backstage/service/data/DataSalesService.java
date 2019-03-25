package com.hema.newretail.backstage.service.data;

import com.hema.newretail.backstage.common.queryparam.data.*;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName DataSalesService
 * @Description 数据统计销量相关
 * @Author ---CWZ
 * @Date 2018/12/17 16:09
 * @Version 1.0
 **/


public interface DataSalesService {

    /**
     *
     * 功能描述: 数据统计销量趋势接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response trendDay(DataSalesTrendCondition condition)throws Exception;

    /**
     *
     * 功能描述: 数据统计销量趋势接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response trendMonth(DataSalesTrendCondition condition) throws Exception;

    /**
     *
     * 功能描述: 数据统计销量列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response list(DataSalesListCondition condition);

    /**
     *
     * 功能描述: 数据统计销量列表excel接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response excel(DataSalesListCondition condition,HttpServletResponse response) throws Exception;

    /**
     *
     * 功能描述: 数据出杯统计列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response cup(CupListCondition condition) throws Exception;

    /**
     *
     * 功能描述: 数据出杯统计列表导出接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response cupExcel(CupListCondition condition,HttpServletResponse response) throws Exception;

    /**
     *
     * 功能描述: 数据出杯统计详细接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response cupDetail(CupDetailCondition condition)throws Exception;
}
