package com.hema.newretail.backstage.service.financial;

import com.hema.newretail.backstage.common.queryparam.financial.MachineCashListCondition;
import com.hema.newretail.backstage.common.queryparam.financial.RetiredCashCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName MachineCashService
 * @Description 设备押金管理
 * @Author ---CWZ
 * @Date 2019/1/7 17:09
 * @Version 1.0
 **/


public interface MachineCashService {


    /**
     *
     * 功能描述:设备押金管理列表
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     * @throws Exception
     */
    Response list(MachineCashListCondition condition)throws Exception;

    /**
     *
     * 功能描述:设备押金管理列表导出（get请求  表单提交）
     *
     * @param condition
     * @param response
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     * @throws Exception
     */
    Response excel(HttpServletResponse response, MachineCashListCondition condition)throws Exception;

    /**
     *
     * 功能描述:批量设置补贴规则
     *
     * @param condition
     * @param request
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    Response batchRule(HttpServletRequest request, RetiredCashCondition condition);

    /**
     *
     * 功能描述:设置补贴规则
     *
     * @param condition
     * @param request
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    Response rule(HttpServletRequest request, RetiredCashCondition condition);

    /**
     *
     * 功能描述:设置违约金
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    Response penalSum(HttpServletRequest request, RetiredCashCondition condition);

    /**
     *
     * 功能描述:停止补贴
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
    Response stopCash(HttpServletRequest request, RetiredCashCondition condition);

    /**
     *
     * 功能描述:停止分润
     *
     * @param
     * @return Response
     * @author  cwz
     * @date  2019/1/7 17:04
     */
   Response stopSeeSplitting(HttpServletRequest request, RetiredCashCondition condition);
}
