package com.hema.newretail.backstage.service.index;

import com.hema.newretail.backstage.common.queryparam.index.BannerAddCondition;
import com.hema.newretail.backstage.common.queryparam.index.BannerEditCondition;
import com.hema.newretail.backstage.common.queryparam.index.BannerListCondition;
import com.hema.newretail.backstage.common.queryparam.system.IdCondition;
import com.hema.newretail.backstage.common.queryparam.system.MachinePicAddCondition;
import com.hema.newretail.backstage.common.queryparam.system.MachinePicEditCondition;
import com.hema.newretail.backstage.common.queryparam.system.MachinePicListCondition;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName BannerService
 * @Description
 * @Author ---CWZ
 * @Date 2018/12/25 15:15
 * @Version 1.0
 **/


public interface BannerService {


    /**
     *
     * 功能描述: 机器大屏广告列表
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     * @throws Exception
     */
    Response machinePic(BannerListCondition condition) throws Exception;

    /**
     *
     * 功能描述: 机器大屏广告add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     * @throws Exception
     */
    Response machinePicAdd(BannerAddCondition condition)throws Exception;

    /**
     *
     * 功能描述: 机器大屏广告add
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     * @throws Exception
     */
    Response machinePicEdit(BannerEditCondition condition)throws Exception;

    /**
     *
     * 功能描述: 机器大屏广告delete
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/24 14:26
     */
    Response machinePicDelete(IdCondition condition);
}
