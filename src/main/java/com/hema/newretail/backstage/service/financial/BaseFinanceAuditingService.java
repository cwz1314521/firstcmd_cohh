package com.hema.newretail.backstage.service.financial;

import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BaseFinanceAuditing;
import com.hema.newretail.backstage.entry.VFinanceAuditing;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cuif @Date：2019/3/4   @Desc:
 **/
public interface BaseFinanceAuditingService {
	/*
	 * 获取待审核数据列表
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	Response getUnCheckedData(VFinanceAuditing baseFinanceAuditing);

	/*
	 * 获取待审核数据详情
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	Response getUnCheckedDataDetail(VFinanceAuditing baseFinanceAuditing);

	/*
	 * 审核操作
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	Response check(BaseFinanceAuditing baseFinanceAuditing, HttpServletRequest request);
}
