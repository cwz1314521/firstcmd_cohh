package com.hema.newretail.backstage.service.agent;

import com.hema.newretail.backstage.common.queryparam.CommomPageCondition;
import com.hema.newretail.backstage.common.queryparam.agent.AddAgentCondition;
import com.hema.newretail.backstage.common.queryparam.agent.AgentListCondition;
import com.hema.newretail.backstage.common.queryparam.agent.EditAgentCondition;
import com.hema.newretail.backstage.common.queryparam.agent.PushCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.agent.AgentUserEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName AgentService
 * @Description 代理公司各接口service接口类
 * @Author ---CWZ
 * @Date 2018/9/21 10:32
 * @Version 1.0
 **/
public interface AgentService {

    /**
     *
     * 功能描述:展示代理公司列表
     *
     * @param agentListCondition
     * @return list
     * @author cwz
     * @date 2018/9/21 10:50
     */
    Response list(AgentListCondition agentListCondition);


    /**
     *
     * 功能描述:添加代理公司
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/9/21 10:50
     */
    Response add(AddAgentCondition condition);


    /**
     *
     * 功能描述:修改代理公司
     *
     * @param editAgentCondition
     * @return Response
     * @author cwz
     * @date 2018/9/21 10:50
     */
    Response edit(EditAgentCondition editAgentCondition);


    /**
     *
     * 功能描述:删除代理公司
     *
     * @param id
     * @return Response
     * @author cwz
     * @date 2018/9/21 10:50
     */
    Response delete(Long id);


    /**
     *
     * 功能描述:代理数据统计
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2018/9/21 10:50
     */
    Response listStatistics();

    /**
     *
     * 功能描述: excel
     *
     * @param agentListCondition
     * @param request
     * @param response
     * @return Response
     * @author cwz
     * @date 2018/10/11 11:49
     * @throws Exception
     */
    Response excel(HttpServletRequest request, HttpServletResponse response, AgentListCondition agentListCondition) throws   Exception;

    /**
     *
     * 功能描述: 消息推送
     *
     * @param pushCondition
     * @return success
     * @author cwz
     * @date 2018/10/26 17:37
     */
    Response push(PushCondition pushCondition);

    /**
     *
     * 功能描述: 推送历史
     *
     * @param condition
     * @return list
     * @author cwz
     * @date 2018/10/26 17:37
     */
    Response pushHistory(CommomPageCondition condition);
}
