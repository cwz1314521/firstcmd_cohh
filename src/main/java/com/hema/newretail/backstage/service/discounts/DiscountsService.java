package com.hema.newretail.backstage.service.discounts;

import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName DiscountsService
 * @Description 优惠券
 * @Author ---CWZ
 * @Date 2019/1/3 17:16
 * @Version 1.0
 **/

public interface DiscountsService {

    /**
     *
     * 功能描述: list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     * @throws Exception
     */
    Response discountsList(DiscountsListCondition condition) throws  Exception;

    /**
     *
     * 功能描述: add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
     Response discountsAdd(DiscountsAddCondition condition);

    /**
     *
     * 功能描述: info
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response discountsInfo(DiscountsInfoCondition condition);

    /**
     *
     * 功能描述: edit
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response discountsEdit(DiscountsEditCondition condition);

    /**
     *
     * 功能描述: check
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response discountsCheck(EventCheckCondition condition);

    /**
     *
     * 功能描述: info
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/3 17:20
     */
    Response discountsDelete(DiscountsInfoCondition condition);
}
