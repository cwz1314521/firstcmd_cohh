package com.hema.newretail.backstage.service;

import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.queryparam.usermanagementparameter.CentralBillListCondition;
import com.hema.newretail.backstage.common.queryparam.usermanagementparameter.MakeOrderCondition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: 程文政
 * @Date: 2018/8/23 11:55
 * @Description:
 * @Version: 1.0
 */
public interface IMakeOrderService {


    /**网格公司all*/
    Response gridCompany();

    /**代理公司all*/
    Response agentCompany();

    /**根据条件查询订单接口，分页显示查询*/
    Response pageOrders(MakeOrderCondition makeOrderCondition)throws Exception;

    /**订单详情*/
    Response orderDetail(String id);

    /**账单详情*/
    Response billDetail(String id);

    /**添加备注*/
    Response saveRemark(String id,String remark);

    /**order备注*/
    Response saveOrderRemark(String id, String remark);

    /**根据条件查询bill接口，分页显示查询*/
    Response centralBillList(CentralBillListCondition centralBillListCondition)throws Exception;


    Response excle(HttpServletRequest request, HttpServletResponse response, CentralBillListCondition centralBillListCondition)throws   Exception;

    Response excleOrder(HttpServletRequest request, HttpServletResponse response, MakeOrderCondition makeOrderCondition)throws   Exception;
}
