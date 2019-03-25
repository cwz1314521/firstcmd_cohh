package com.hema.newretail.backstage.service.index;

import com.hema.newretail.backstage.common.queryparam.diy.IdCondition;
import com.hema.newretail.backstage.common.queryparam.index.*;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName PopupService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/11 14:20
 * @Version 1.0
 **/


public interface PopupService {


    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    Response list(PopupListCondition condition) throws Exception;

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    Response add(PopupAddCondition condition) throws Exception;

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
    Response edit(PopupEditCondition condition)throws Exception;

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/3/11 13:58
     */
   Response delete(IdCondition condition);
}
