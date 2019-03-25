package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseFinanceAuditing;
import com.hema.newretail.backstage.entry.BusiCouponGiveAuditing;

public interface BusiCouponGiveAuditingMapper {
	int deleteByPrimaryKey(Long id);

	int insert(BusiCouponGiveAuditing record);

	int insertSelective(BusiCouponGiveAuditing record);

	BusiCouponGiveAuditing selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BusiCouponGiveAuditing record);

	int updateByPrimaryKeyWithBLOBs(BusiCouponGiveAuditing record);

	int updateByPrimaryKey(BusiCouponGiveAuditing record);

	/*
	 * 获取未审核的优惠券细节
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-05
	 */
	BusiCouponGiveAuditing getUnCheckedDataDetail(Long id);

	/**
	 * 优惠券审核
	 * @param baseFinanceAuditing
	 * @return
	 */
	int checkCouponFinally(BaseFinanceAuditing baseFinanceAuditing);
}