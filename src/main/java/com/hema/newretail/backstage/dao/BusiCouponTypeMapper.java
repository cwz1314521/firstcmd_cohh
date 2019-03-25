package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.discounts.BusiCouponTypeEntry;

public interface BusiCouponTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusiCouponTypeEntry record);

    int insertSelective(BusiCouponTypeEntry record);

    BusiCouponTypeEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiCouponTypeEntry record);

    int updateByPrimaryKey(BusiCouponTypeEntry record);
}