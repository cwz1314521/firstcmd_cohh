package com.hema.newretail.backstage.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author cuif
 * @描述:
 * @创建日期： 2018/11/9
 * TODO：

 * @备注：
 **/
@ApiModel(value = "分页工具类")
public class BasePage {
	/**
	 * 分页的页数
	 */
	@ApiModelProperty(value = "当前页数",required = true)
	//@Range(min = 0,  message = "从第一页开始")
	private int pageNum;
	/**
	 * 条数
	 */
	@ApiModelProperty(value = "一页的数据大小",required = true)
	//@Range(min = 0,  message = "最少一条数据")
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
