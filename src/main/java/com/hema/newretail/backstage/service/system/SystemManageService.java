package com.hema.newretail.backstage.service.system;

import com.hema.newretail.backstage.common.queryparam.system.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BaseUpgrade;

/**
 * @Department 新零售
 * @ClassName SystemManageService
 * @Description 系统管理相关Service
 * @Author ---CWZ
 * @Date 2018/12/24 13:54
 * @Version 1.0
 **/


public interface SystemManageService {

	/**
	 * 功能描述: 机器大屏广告列表
	 *
	 * @param condition
	 * @return Response
	 * @author cwz
	 * @date 2018/12/24 14:26
	 */
	Response machinePic(MachinePicListCondition condition);

	/**
	 * 功能描述: 机器大屏广告add
	 *
	 * @param condition
	 * @return Response
	 * @author cwz
	 * @date 2018/12/24 14:26
	 */
	Response machinePicAdd(MachinePicAddCondition condition) throws Exception;

	/**
	 * 功能描述: 机器大屏广告add
	 *
	 * @param condition
	 * @return Response
	 * @author cwz
	 * @date 2018/12/24 14:26
	 */
	Response machinePicEdit(MachinePicEditCondition condition) throws Exception;

	/**
	 * 功能描述: 机器大屏广告delete
	 *
	 * @param condition
	 * @return Response
	 * @author cwz
	 * @date 2018/12/24 14:26
	 */
	Response machinePicDelete(IdCondition condition);

	/**
	 * app升级管理--获取列表
	 *
	 * @param condition
	 * @return
	 */
	Response baseUpgradeList(BaseUpgrade condition);

	/*
	 * app升级管理--新增/编辑
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-05
	 */
	Response baseUpgradeEdit(BaseUpgrade condition);

	/**
	 * app升级管理--详情
	 */
	Response baseUpgradeDetail(BaseUpgrade condition);

	/**
	 * 删除单条数据
	 * @param condition
	 * @return
	 */
	Response baseUpgradeDelete(BaseUpgrade condition);
}
