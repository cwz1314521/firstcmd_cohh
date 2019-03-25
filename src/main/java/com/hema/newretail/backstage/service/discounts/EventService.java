package com.hema.newretail.backstage.service.discounts;

import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpSession;

/**
 * @Department 新零售
 * @ClassName EventService
 * @Description 活动
 * @Author ---CWZ
 * @Date 2019/1/3 17:15
 * @Version 1.0
 **/
public interface EventService {


    /**
     *
     * 功能描述: list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response eventList(EventListCondition condition)throws Exception;

    /**
     *
     * 功能描述: add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     * @throws Exception
     */
    Response eventAdd(EventAddCondition condition)throws Exception;

    /**
     *
     * 功能描述: info
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response eventInfo(EventInfoCondition condition);

    /**
     *
     * 功能描述: edit
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response eventEdit(EventEditCondition condition)throws Exception;

    /**
     *
     * 功能描述: check
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response eventCheck(EventCheckCondition condition, HttpSession session);

    /**
     *
     * 功能描述: stop
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response eventStop(EventInfoCondition condition);

    /**
     *
     * 功能描述: delete
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response eventDelete( EventInfoCondition condition);
}
