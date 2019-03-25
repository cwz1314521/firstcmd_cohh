package com.hema.newretail.backstage.service.common;/**
 * @Department 新零售
 * @ClassName QueryService
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/11 10:15
 * @Version 1.0
 **/

import com.hema.newretail.backstage.common.queryparam.common.CompanyNameCondition;
import com.hema.newretail.backstage.common.queryparam.common.MapGeohashTagCondition;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @ClassName QueryService
 * @Description 预查询公共接口Service
 * @Author CWZ
 * @Date 2018/12/11 10:15
 * @Version 1.0
 **/
public interface QueryService {

    /**
     * 功能描述: 代理公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    Response agent(CompanyNameCondition condition);

    /**
     * 功能描述: 网格公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    Response company(CompanyNameCondition condition);

    /**
     * 功能描述: 网格公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    Response grid(CompanyNameCondition condition);


    /**
     * 功能描述: 网格公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    Response allMachine(CompanyNameCondition condition);

    /**
     * 功能描述: 片区实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    Response allZone(CompanyNameCondition condition);

    /**
     * 查询所有的饮品分类
     *
     * @return r
     */
    Response queryAllClassify();

    /**
     * 功能描述: 优惠券
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    Response allDiscounts();

    /**
     * 功能描述: 饮品名
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    Response allMenu();

    /**
     * 功能描述: 组织架构
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    Response allOrganization();


    /**
     * 功能描述:
     *
     * @param
     * @return
     * @author cwz
     * @date 2019/2/26 15:47
     */
    Response allArticleType();

    /**
     * 查询地图可视范围内的所有设备坐标
     *
     * @param condition 地图可视范围内的geohash
     * @return Response
     */
    Response queryAllTag(MapGeohashTagCondition condition);

    /**
     * 查询所有的活动规则
     *
     * @return
     */
    Response queryAllActivityRule();
}
