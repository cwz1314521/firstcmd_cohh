package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseFinanceAuditing;
import com.hema.newretail.backstage.entry.VFinanceAuditing;

import java.util.List;

public interface VFinanceAuditingMapper {
	int insert(VFinanceAuditing record);

	int insertSelective(VFinanceAuditing record);

	/*
	 * 获取未审核数据
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	List<BaseFinanceAuditing> getUnCheckedData(VFinanceAuditing baseFinanceAuditing);
}