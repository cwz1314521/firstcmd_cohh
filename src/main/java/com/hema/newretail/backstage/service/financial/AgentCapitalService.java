package com.hema.newretail.backstage.service.financial;

import com.hema.newretail.backstage.common.queryparam.financial.AgentCapitalCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName AgentCapitalService
 * @Description
 * @Author ---CWZ
 * @Date 2019/1/9 18:47
 * @Version 1.0
 **/


public interface AgentCapitalService {

    /**
     *
     * 功能描述:  list
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     * @throws Exception
     */
    Response list(AgentCapitalCondition condition)throws Exception;


    /**
     *
     * 功能描述:清算
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     */
    Response clearing(AgentCapitalCondition condition);

    /**
     *
     * 功能描述:修改信息
     *
     * @param condition
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     */
    Response edit( AgentCapitalCondition condition);

    /**
     *
     * 功能描述:  excel
     *
     * @param condition
     * @param response
     * @return Response
     * @author  cwz
     * @date  2019/1/9 17:43
     * @throws Exception
     */
    Response excel(HttpServletResponse response, AgentCapitalCondition condition)throws Exception;
}
