package com.hema.newretail.backstage.service.authority;

import com.hema.newretail.backstage.common.queryparam.authority.OperationLogsCondition;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.authority
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 16:02
 */
public interface OperationLogsService {

    /**
     * 查询操作记录
     *
     * @param vo
     * @return
     */
    Response queryList(OperationLogsCondition vo);
}
