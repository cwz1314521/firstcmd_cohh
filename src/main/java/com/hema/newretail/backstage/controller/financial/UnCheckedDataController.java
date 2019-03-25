package com.hema.newretail.backstage.controller.financial;

import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.entry.BaseFinanceAuditing;
import com.hema.newretail.backstage.entry.VFinanceAuditing;
import com.hema.newretail.backstage.service.financial.BaseFinanceAuditingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cuif  @Date：2019/3/4 @Desc: 财务管理
 **/
@RestController
@Api(description = "待审核数据")
@RequestMapping("/UnCheckedData")
public class UnCheckedDataController {

	@Autowired
	private BaseFinanceAuditingService baseFinanceAuditingService;


	/*
	 * 获取未审核的数据列表
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	@PostMapping ("/getUnCheckedData")
	@ApiOperation("获取未审核的数据列表")
	public Response getUnCheckedData(@RequestBody VFinanceAuditing vFinanceAuditing) {
		return baseFinanceAuditingService.getUnCheckedData(vFinanceAuditing);
	}

	/*
	 * 获取未审核的数据列表---查看详情
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	@PostMapping ("/getUnCheckedDataDetail")
	@ApiOperation("获取未审核的数据列表---查看详情")
	public Response getUnCheckedDataDetail(@RequestBody VFinanceAuditing vFinanceAuditing) {
		return baseFinanceAuditingService.getUnCheckedDataDetail(vFinanceAuditing);
	}

	/*
	 * 审核--内容-操作
	 * @param:  * @param null
	 * @return
	 * @author cuif
	 * @date 2019-03-04
	 */
	@PostMapping ("/checkUnCheckedDataDetail")
	@ApiOperation("审核--内容-操作")
	public Response check(@RequestBody BaseFinanceAuditing baseFinanceAuditing,HttpServletRequest request) {
		return baseFinanceAuditingService.check(baseFinanceAuditing,request);
	}
}
