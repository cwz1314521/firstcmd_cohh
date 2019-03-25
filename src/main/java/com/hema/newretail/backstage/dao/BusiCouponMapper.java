package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.discounts.BusiCouponEntry;
import com.hema.newretail.backstage.model.memberManager.UserCouponListBo;

import java.util.List;
import java.util.Map;

import java.util.Map;

public interface BusiCouponMapper {

    Map selectByBillDetail(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(BusiCouponEntry record);

    int insertSelective(BusiCouponEntry record);

    BusiCouponEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiCouponEntry record);

    int updateByPrimaryKey(BusiCouponEntry record);

    BusiCouponEntry getBusiCouponByOederId(String id);

    /**
     * 查询用户领取的优惠券数量
     * @param receivePerson
     * @return
     */
    Long selectCouponNumByUserId(String receivePerson);

    /**
     *
     * @param receivePerson
     * @return
     */
    List<UserCouponListBo> selectCouponDataByUserId(String receivePerson);
}