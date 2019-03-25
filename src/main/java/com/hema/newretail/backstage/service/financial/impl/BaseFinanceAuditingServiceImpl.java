package com.hema.newretail.backstage.service.financial.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.*;
import com.hema.newretail.backstage.entry.BaseFinanceAuditing;
import com.hema.newretail.backstage.entry.BusiActivityRuleEntry;
import com.hema.newretail.backstage.entry.BusiCouponGiveAuditing;
import com.hema.newretail.backstage.entry.VFinanceAuditing;
import com.hema.newretail.backstage.entry.discounts.BusiActivityEntry;
import com.hema.newretail.backstage.service.financial.BaseFinanceAuditingService;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cuif  @Date：2019/3/4 @Desc:
 **/
@Service
@Slf4j
public class BaseFinanceAuditingServiceImpl implements BaseFinanceAuditingService {
	@Autowired
	private VFinanceAuditingMapper vFinanceAuditingMapper;
	@Autowired
	private BusiActivityMapper busiActivityMapper;
	@Autowired
	private BusiCouponGiveAuditingMapper busiCouponGiveAuditingMapper;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private BusiActivityRuleMapper busiActivityRuleMapper;
	@Autowired
	private BusiCouponAuditingMapper busiCouponAuditingMapper;

	@Override
	public Response getUnCheckedData(VFinanceAuditing baseFinanceAuditing) {
		Page<Object> objects = PageHelper.startPage(baseFinanceAuditing.getPageNum(), baseFinanceAuditing.getPageSize());
		List<BaseFinanceAuditing> list = null;
		try {
			list = vFinanceAuditingMapper.getUnCheckedData(baseFinanceAuditing);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.failure("系统异常");
		}
		return Response.success(list, objects.getTotal(), baseFinanceAuditing.getPageSize(), baseFinanceAuditing.getPageNum());
	}

	@Override
	public Response getUnCheckedDataDetail(VFinanceAuditing baseFinanceAuditing) {
		String type = String.valueOf(baseFinanceAuditing.getType());
		Long id = baseFinanceAuditing.getId();
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		//0 活动 1优惠券
		if ("0".equals(type)) {
			BusiActivityEntry busiActivityEntry = busiActivityMapper.selectByPrimaryKey(id);
			BusiActivityRuleEntry busiActivityRuleEntry = busiActivityRuleMapper.selectByPrimaryKey(busiActivityEntry.getActivityRuleId());
			busiActivityEntry.setActivityRule(busiActivityRuleEntry.getRuleName());
			map.put("data", busiActivityEntry);
			return Response.success(map);
		}
		BusiCouponGiveAuditing busiCouponGiveAuditing = busiCouponGiveAuditingMapper.getUnCheckedDataDetail(id);
		Long auditorId = busiCouponGiveAuditing.getAuditorId();

		String givePersonId = busiCouponGiveAuditing.getGivePersonId();
		List<String> list = Arrays.asList(givePersonId.split(","));
		List<BasicDBObject> basicDBObjects = mongoTemplate.find(new Query(Criteria.where("_id").in(list)), BasicDBObject.class, "userManagementData");
		List<String> phoneNumber = basicDBObjects.stream().map(dto -> dto.getString("phoneNumber")).collect(Collectors.toList());
		busiCouponGiveAuditing.setPhoneList(phoneNumber);
		map.put("data", busiCouponGiveAuditing);
		return Response.success(map);
	}

	@Override
	public Response check(BaseFinanceAuditing baseFinanceAuditing, HttpServletRequest request) {
		String type = baseFinanceAuditing.getType();
//		String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
//		if(userinfoJson == null){
//			log.error("未检测到登录人数据");
//			return Response.failure("未检测到登录人数据");
//		}
//		JSONObject jsStr = JSONObject.parseObject(userinfoJson);
//		String id = jsStr.getString("id");
//		String userName = jsStr.getString("userName");
//		baseFinanceAuditing.setAuditorId(id);
//		baseFinanceAuditing.setAuditor(userName);
		Date date = new Date();
		baseFinanceAuditing.setAuditTime(date);
		baseFinanceAuditing.setGmtModified(date);
		if ("0".equals(type)) {
			int a = busiActivityMapper.checkActivityFinally(baseFinanceAuditing);
			return Response.success();
		}
		int b = busiCouponGiveAuditingMapper.checkCouponFinally(baseFinanceAuditing);
		return Response.success();
	}
}
