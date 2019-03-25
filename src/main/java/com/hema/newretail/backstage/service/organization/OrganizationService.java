package com.hema.newretail.backstage.service.organization;

import com.hema.newretail.backstage.common.queryparam.organization.*;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName OrganizationService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/15 16:03
 * @Version 1.0
 **/


public interface OrganizationService {
    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    Response list();

    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    Response add(OrganizationAddCondition condition);

    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    Response edit(OrganizationEditCondition condition);

    /**
     *
     * 功能描述: 组织架构列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/15 15:24
     */
    Response delete(OrganizationDeleteCondition condition);
}
