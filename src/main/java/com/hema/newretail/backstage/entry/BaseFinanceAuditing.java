package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.util.Date;

/**
 * @author cuif  @Date：2019/3/4 @Desc:
 **/
@Data
public class BaseFinanceAuditing {
	/**
	 * 序号
	 */
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 申请时间
	 */
	private String applyTime;
	/**
	 * 提交人
	 */
	private String applicant;
	/**
	 * 审核状态
	 */
	private String auditStatus;
	/**
	 *  审核人
	 */
	private String auditor;
	/**
	 *  审核人-id
	 */
	private String auditorId;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核说明（备注）
	 */
	private String auditDesc;

	/***
	 * 修改时间
	 */
	private Date gmtModified;
}
