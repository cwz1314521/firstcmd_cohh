package com.hema.newretail.backstage.service.erp;

import com.hema.newretail.backstage.common.queryparam.erp.InStoreListCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStoreNumCondition;
import com.hema.newretail.backstage.common.queryparam.erp.InStorePreAllCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @Department 新零售
 * @ClassName OutStoreService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/11/27 14:09
 * @Version 1.0
 **/
public interface OutStoreService {
    /**
     *
     * 功能描述:分后台  入库记录--待入库列表
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    Response inStoreLoadList();

    /**
     *
     * 功能描述: 分后台---待出库--列表
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/27 11:43
     */
    Response waitOutstoreList(InStoreListCondition inStoreListCondition);

    /**
     *
     * 功能描述: 分后台---待出库--列表
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/11/27 11:43
     */
    Response todayOutstore();

    /**
     *
     * 功能描述: 分后台--扫码出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    Response outstorePre(InStorePreAllCondition inStoreListCondition);

    /**
     *
     * 功能描述: 分后台--输码出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    Response outstoreNum(HttpServletRequest request, InStoreNumCondition inStoreNumCondition);

    /**
     *
     * 功能描述: 分后台-- 确认提交出库
     *
     * @param: InStoreListCondition
     * @return: list
     * @author: cwz
     * @date: 2018/11/2 14:07
     */
    Response outstore(HttpServletRequest request);
}
