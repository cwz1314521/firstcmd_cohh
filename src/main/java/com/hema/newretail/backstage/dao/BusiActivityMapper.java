package com.hema.newretail.backstage.dao;
/**
 * @author cwz
 */

import com.hema.newretail.backstage.common.queryparam.discounts.EventListCondition;
import com.hema.newretail.backstage.entry.BaseFinanceAuditing;
import com.hema.newretail.backstage.entry.discounts.BusiActivityEntry;
import com.hema.newretail.backstage.model.discounts.ActivityInfoBo;
import com.hema.newretail.backstage.model.discounts.ActivityListBo;

import java.util.List;
import java.util.Map;

public interface BusiActivityMapper {

    /**
     *
     * 功能描述: list
     *
     * @param  condition
     * @return  List<ActivityListBo>
     * @author  cwz
     * @date  2019/1/4 11:04
     */
    List<ActivityListBo>  selectList(EventListCondition condition);

    /**
     *
     * 功能描述: list
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/1/4 11:04
     */
    List<Map>  selectAll();

    /**
     *
     * 功能描述: stop
     *
     * @param  id
     * @return  int
     * @author  cwz
     * @date  2019/1/4 13:25
     */
    int stop(Long id);

    /**
     *
     * 功能描述: info
     *
     * @param  id
     * @return  ActivityInfoBo
     * @author  cwz
     * @date  2019/1/4 13:25
     */
    ActivityInfoBo selectInfo(Long id);



    int updateDelete(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(BusiActivityEntry record);

    int insertSelective(BusiActivityEntry record);

    BusiActivityEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiActivityEntry record);

	int updateByPrimaryKey(BusiActivityEntry record);

	/*
	 * 最终审核
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-05
	 */
	int checkActivityFinally(BaseFinanceAuditing baseFinanceAuditing);
}