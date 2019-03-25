package com.hema.newretail.backstage.service.financial;

import com.hema.newretail.backstage.common.queryparam.financial.AgentTransactionRecordCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.financial
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-11 15:57
 */
public interface AgentTransactionRecordService {
    /**
     * 查询
     *
     * @param vo
     * @return
     */
    Response queryList(AgentTransactionRecordCondition vo);

    /**
     * 导出
     *
     * @param vo
     * @param response
     * @return
     */
    Response excel(AgentTransactionRecordCondition vo, HttpServletResponse response);
}
