package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.discounts.RefMenuCouponEntry;

import java.util.List;

public interface RefMenuCouponMapper {



    int  deleteByCId(Long couponId);

    int deleteByPrimaryKey(Long id);

    int insert(RefMenuCouponEntry record);

    int insertSelective(RefMenuCouponEntry record);

    RefMenuCouponEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RefMenuCouponEntry record);

    int updateByPrimaryKey(RefMenuCouponEntry record);
}