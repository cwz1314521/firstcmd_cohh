package com.hema.newretail.backstage.service.discounts;

import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @Department 新零售
 * @ClassName EventRuleService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/28 14:23
 * @Version 1.0
 **/

public interface EventRuleService {


    /**
     *
     * 功能描述: list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    Response eventRuleList(EventRuleListCondition condition)throws Exception;

    /**
     *
     * 功能描述: add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    Response eventRuleAdd(HttpServletRequest request, EventAddRuleCondition condition);

    /**
     *
     * 功能描述: edit
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    Response eventRuleEdit(EventEditRuleCondition condition);

    /**
     *
     * 功能描述: delete
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/2/28 11:11
     */
    Response eventRuleDelete(EventDeleteRuleCondition condition);

    /**
     * 功能描述: 查询当前占用饮品和设备
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    Response menuOrMachine(MenuOrMachineCondition condition);
}
