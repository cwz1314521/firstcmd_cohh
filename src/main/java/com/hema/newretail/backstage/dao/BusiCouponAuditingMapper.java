package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.discounts.DiscountsListCondition;
import com.hema.newretail.backstage.common.queryparam.discounts.EventCheckCondition;
import com.hema.newretail.backstage.entry.discounts.BusiCouponAuditingEntry;
import com.hema.newretail.backstage.model.discounts.DiscountsListBo;

import java.util.List;
import java.util.Map;

public interface BusiCouponAuditingMapper {


    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return  List<DiscountsListBo>
     * @author  cwz
     * @date  2019/1/4 19:12
     */
    List<DiscountsListBo> list(DiscountsListCondition condition);

    /**
     *
     * 功能描述: check
     *
     * @param  condition
     * @return  int
     * @author  cwz
     * @date  2019/1/4 11:04
     */
    int check(EventCheckCondition condition);

    /**
     *
     * 功能描述: delete
     *
     * @param  id
     * @return  int
     * @author  cwz
     * @date  2019/1/4 11:04
     */
    int updateDelete(Long id);


    List<Map> selectMenu(Long id);


    List<BusiCouponAuditingEntry> all();

    int deleteByPrimaryKey(Long id);

    int insert(BusiCouponAuditingEntry record);

    int insertSelective(BusiCouponAuditingEntry record);

    BusiCouponAuditingEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiCouponAuditingEntry record);

    int updateByPrimaryKey(BusiCouponAuditingEntry record);
}