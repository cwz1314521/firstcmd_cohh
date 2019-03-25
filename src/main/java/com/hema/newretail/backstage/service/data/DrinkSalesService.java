package com.hema.newretail.backstage.service.data;

import com.hema.newretail.backstage.common.queryparam.data.*;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName DrinkSalesService
 * @Description 数据统计销量相关
 * @Author ---yingyan
 * @Date 2019/3/16
 * @Version 1.0
 **/


public interface DrinkSalesService {

    /**
     *
     * 功能描述: 设备饮品销售列表接口
     *
     * @param  condition
     * @return  Response
     * @author  yingyan
     * @date  2019/3/16
     * @throws Exception
     */
    Response drink(DrinkSalesListCondition condition) throws Exception;

    /**
     *
     * 功能描述: 设备饮品销售列表导出接口
     *
     * @param  condition
     * @return  Response
     * @author  yingyan
     * @date  2019/3/16
     * @throws Exception
     */
    Response drinkExcel(DrinkSalesListCondition condition, HttpServletResponse response) throws Exception;

    /**
     *
     * 功能描述: 设备饮品销售详细接口
     *
     * @param  condition
     * @return  Response
     * @author  yingyan
     * @date  2019/3/16
     * @throws Exception
     */
    Response drinkDetail(DrinkDetailCondition condition)throws Exception;
}
