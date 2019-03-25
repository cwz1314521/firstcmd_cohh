package com.hema.newretail.backstage.service.memberManager.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.mongodb.MongoDBPageModel;
import com.hema.newretail.backstage.common.mongodb.SpringbootMongoDBPageable;
import com.hema.newretail.backstage.common.queryparam.memberManager.UserManagementCondition;
import com.hema.newretail.backstage.common.queryparam.memberManager.UserManagementDetailCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.excel.PoiExcelHelper;
import com.hema.newretail.backstage.dao.BusiCouponAuditingMapper;
import com.hema.newretail.backstage.dao.BusiCouponGiveAuditingMapper;
import com.hema.newretail.backstage.dao.BusiCouponMapper;
import com.hema.newretail.backstage.entry.BusiCouponGiveAuditing;
import com.hema.newretail.backstage.entry.discounts.BusiCouponAuditingEntry;
import com.hema.newretail.backstage.model.memberManager.UserCouponListBo;
import com.hema.newretail.backstage.model.memberManager.UserListBo;
import com.hema.newretail.backstage.model.memberManager.UserManagementData;
import com.hema.newretail.backstage.service.memberManager.UserManagementDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.memberManager.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-15 11:18
 */
@Service
public class UserManagementDataServiceImpl implements UserManagementDataService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BusiCouponMapper busiCouponMapper;
    @Autowired
    private BusiCouponGiveAuditingMapper busiCouponGiveAuditingMapper;
    @Autowired
    private BusiCouponAuditingMapper busiCouponAuditingMapper;

    private static final String FROZEN = "1";//默认冻结状态码
    private static final String UNFREEZE = "0";//默认解冻状态码
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Response queryList(UserManagementCondition condition) {
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagenumber(condition.getPageNum());
        pm.setPagesize(condition.getPageSize());
        List<Sort.Order> orders = new ArrayList<>();  //排序
        orders.add(new Sort.Order(Sort.Direction.DESC, "age"));
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pageable.setPage(pm);
        //开始拼装查询数据
        Query query = null;
        try {
            query = assembleQueryData(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询总条数 total
        Long count = mongoTemplate.count(null == query ? new Query() : query, UserManagementData.class, "userManagementData");

        List<UserManagementData> list = mongoTemplate.find(null == query ? new Query().with(pageable) : query.with(pageable), UserManagementData.class, "userManagementData");

        return Response.success(getUserList(list), count, condition.getPageSize(), condition.getPageNum());
    }

    @Override
    public Response batchFreeze(String ids) {
        return updateUserStatus(ids, FROZEN);
    }

    @Override
    public Response batchRelease(String ids) {
        return updateUserStatus(ids, UNFREEZE);
    }

    @Override
    public Response excel(UserManagementCondition condition, HttpServletResponse response) {
        Query query = null;
        try {
            query = assembleQueryData(condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<UserManagementData> list = mongoTemplate.find(null == query ? new Query() : query, UserManagementData.class, "userManagementData");

        List<UserListBo> resultList = getUserList(list);
        String[] titles = {"用户ID", "昵称", "手机号", "优惠券", "饮品卡", "配方", "关注", "消费次数", "邀请人", "注册时间", "最近登录时间", "登录次数", "来源", "状态", "设备号"};
        PoiExcelHelper excelHelper = new PoiExcelHelper("用户数据列表", titles);
        if (null != list) {
            // 创建表头
            excelHelper.createTitle();
            int rowNum = 1;
            for (UserListBo data : resultList) {
                // 生成excel内容
                int index = 0;
                String[] contents = new String[15];
                contents[index++] = null == data.getId() ? "" : data.getId();
                String phoneNumber = null == data.getPhoneNumber() ? "" : data.getPhoneNumber();
                System.out.println("appNickName=" + phoneNumber);
                String nickname = null == data.getNickname() ? "" : data.getNickname();
                String appNickName = null == data.getAppNickName() ? "" : data.getAppNickName();
                if (null != data.getRegisterSource() && 0 == data.getRegisterSource()) {
                    // 小程序
                    contents[index] = (StringUtils.isEmpty(nickname) ? "" : nickname + "/");
                } else {
                    contents[index] = (StringUtils.isEmpty(appNickName) ? "" : appNickName + "/");
                }
                index++;
                contents[index++] = phoneNumber;
                contents[index++] = null == data.getCouponNum() ? "0" : data.getCouponNum().toString();
                contents[index++] = null == data.getMenuCardNum() ? "0" : data.getMenuCardNum().toString();
                contents[index++] = null == data.getBoxGroupNum() ? "0" : data.getBoxGroupNum().toString();
                contents[index++] = null == data.getConcernNum() ? "0" : data.getConcernNum().toString();
                contents[index++] = null == data.getConsumeNum() ? "0" : data.getConsumeNum().toString();
                contents[index++] = StringUtils.isEmpty(data.getShareOpenName()) ? "" : data.getShareOpenName();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                contents[index++] = null == data.getRegistrationDate() ? "" : sdf.format(data.getRegistrationDate());
                contents[index++] = null == data.getLastLoginDate() ? "" : sdf.format(data.getLastLoginDate());
                contents[index++] = null == data.getLoginCount() ? "0" : data.getLoginCount().toString();
                String registerSource = "";
                if (null == data.getRegisterSource()) {
                    registerSource = "";
                } else if (0 == data.getRegisterSource()) {
                    registerSource = "小程序";
                } else if (1 == data.getRegisterSource()) {
                    registerSource = "安卓";
                } else if (2 == data.getRegisterSource()) {
                    registerSource = "IOS";
                }
                contents[index++] = registerSource;
                String status = "";
                if (null == data.getStatus()) {
                    status = "";
                } else if ("0".equals(data.getStatus())) {
                    status = "正常";
                } else if ("1".equals(data.getStatus())) {
                    status = "冻结";
                }
                contents[index++] = status;
                contents[index++] = StringUtils.isEmpty(data.getPhoneDeviceNumber()) ? "" : data.getPhoneDeviceNumber();
                excelHelper.createData(rowNum++, contents);
            }
        }
        //浏览器下载excel
        excelHelper.buildExcelDocument(response);
        return Response.success();
    }

    @Override
    public Response queryCouponList(UserManagementDetailCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<UserCouponListBo> list = busiCouponMapper.selectCouponDataByUserId(condition.getId());
        return Response.success(list, ((Page) list).getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response addGiveCoupon(BusiCouponGiveAuditing vo) {
        int surplusNum = 0;
        if (null != vo.getCouponAuditingId() && null != vo.getCouponGiveNumber()) {
            BusiCouponAuditingEntry busiCouponAuditingEntry = busiCouponAuditingMapper.selectByPrimaryKey(vo.getCouponAuditingId());
            // 计算优惠券剩余数量
            String[] givePersons = vo.getGivePersonId().split(",");
            if (givePersons.length == 0) {
                return Response.failure("赠送张数不应该小于或等于零");
            }
            // 总赠送数量
            int total = vo.getCouponGiveNumber() * givePersons.length;

            if (null == busiCouponAuditingEntry.getSurplusNum() || busiCouponAuditingEntry.getSurplusNum() < total) {
                return Response.failure("优惠券数量不足");
            } else {
                surplusNum = busiCouponAuditingEntry.getSurplusNum() - total;
            }
        }
        vo.setGmtCreate(new Date());
        vo.setGmtModify(new Date());
        vo.setApplyTime(new Date());
        vo.setAuditStatus(1);
        int num = busiCouponGiveAuditingMapper.insert(vo);
        if (num > 0) {
            BusiCouponAuditingEntry busiCouponAuditingEntry = new BusiCouponAuditingEntry();
            busiCouponAuditingEntry.setId(vo.getCouponAuditingId());
            busiCouponAuditingEntry.setSurplusNum(surplusNum);
            busiCouponAuditingEntry.setGmtModified(new Date());
            int count = busiCouponAuditingMapper.updateByPrimaryKeySelective(busiCouponAuditingEntry);
            if (count <= 0) {
                throw new RuntimeException("赠券失败");
            }
        }
        return Response.success();
    }

    /**
     * 重组用户信息
     *
     * @param list
     * @return
     */
    private List<UserListBo> getUserList(List<UserManagementData> list) {
        List<UserListBo> resultList = new ArrayList<>(16);
        if (null != list) {
            for (UserManagementData data : list) {
                UserListBo bo = new UserListBo();
                BeanUtils.copyProperties(data, bo);
                Long couponNum = busiCouponMapper.selectCouponNumByUserId(bo.getId());
                // 优惠券
                bo.setCouponNum(couponNum.intValue());
                // 配方
                //TODO
                bo.setBoxGroupNum(0);

                // 关注
                Query concernQuery = new Query();
                concernQuery.addCriteria(Criteria.where("userId").is(bo.getId()));
                Long concernNum = mongoTemplate.count(concernQuery, "userRelationData");
                bo.setConcernNum(concernNum.intValue());
                // 消费
                Query billQuery = new Query();
                billQuery.addCriteria(Criteria.where("userId").is(bo.getId()));
                Long consumeNum = mongoTemplate.count(billQuery, "billData");
                bo.setConsumeNum(consumeNum.intValue());

                // 邀请人
                //TODO
//                Query sharQuery = new Query();
//                sharQuery.addCriteria(Criteria.where("shareOpenId").exists(true).andOperator(Criteria.where("shareOpenId").is(data.getShareOpenId())));
                bo.setShareOpenName("");
                resultList.add(bo);
            }
        }
        return resultList;
    }

    /**
     * 拼装查询条件
     *
     * @param condition
     * @return
     * @throws Exception
     */
    private Query assembleQueryData(UserManagementCondition condition) throws Exception {
        Query query = new Query();
        //开始拼装查询数据
        //判断昵称是否为空String str = "";
        if (condition.getNickname() != null && !"".equals(condition.getNickname())) {
            boolean isNum = condition.getNickname().matches("[0-9]+");
            if (isNum) {
                query.addCriteria(Criteria.where("phoneNumber").is(condition.getNickname()));
            } else {
                query.addCriteria(Criteria.where("nickname").is(condition.getNickname()).orOperator(Criteria.where("appNickName").is(condition.getNickname())));
            }
        }
        //判断注册时间区间
        //前后都不为空
        if (condition.getPreRegistrationDate() != null && condition.getRegistrationDate() != null) {
            Date preDate = sdf.parse(condition.getPreRegistrationDate());
            Date date = sdf.parse(condition.getRegistrationDate());
            //注册时间查询
            query.addCriteria(Criteria.where("registrationDate").gte(preDate).lte(date));
        }
        //前为空
        if (condition.getPreRegistrationDate() == null && condition.getRegistrationDate() != null) {
            Date date = sdf.parse(condition.getRegistrationDate());
            //注册时间查询
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -30);
            query.addCriteria(Criteria.where("registrationDate").gte(calendar.getTime()).lte(date));
        }
        //后为空
        if (condition.getPreRegistrationDate() != null && condition.getRegistrationDate() == null) {
            Date preDate = sdf.parse(condition.getPreRegistrationDate());
            //注册时间查询
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(preDate);
            calendar.add(Calendar.DATE, 30);
            query.addCriteria(Criteria.where("registrationDate").gte(preDate).lte(calendar.getTime()));
        }
        //状态判断查询
        if (condition.getStatus() != null && !"".equals(condition.getStatus())) {
            //状态查询
            query.addCriteria(Criteria.where("status").is(condition.getStatus()));
        }
        if (null != condition.getRegisterSource()) {
            query.addCriteria(Criteria.where("registerSource").is(condition.getRegisterSource()));
        }
        return query;
    }

    /**
     * 批量更新用户状态
     *
     * @param ids
     * @param status
     * @return
     */
    private Response updateUserStatus(String ids, String status) {
        String[] idArray = ids.split(",");
        Update update = new Update();
        Query query = new Query(Criteria.where("_id").in(idArray));
        update.set("status", status);
        mongoTemplate.updateMulti(query, update, "userManagementData");
        return Response.success();
    }


}
