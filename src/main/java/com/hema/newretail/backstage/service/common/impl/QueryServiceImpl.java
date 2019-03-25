package com.hema.newretail.backstage.service.common.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.common.CompanyNameCondition;
import com.hema.newretail.backstage.common.queryparam.common.MapGeohashTagCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.*;
import com.hema.newretail.backstage.entry.BusiIngredientMenuEntry;
import com.hema.newretail.backstage.entry.discounts.BusiCouponAuditingEntry;
import com.hema.newretail.backstage.model.common.AgentCompanyBo;
import com.hema.newretail.backstage.model.common.GridCompanyBo;
import com.hema.newretail.backstage.model.erp.ListManufacturerBo;
import com.hema.newretail.backstage.model.menu.AllMenuClassifyBo;
import com.hema.newretail.backstage.model.organization.BaseOrganizationBo;
import com.hema.newretail.backstage.service.common.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QueryServiceImpl
 * @Description 预查询公共接口ServiceIMPL
 * @Author CWZ
 * @Date 2018/12/11 10:15
 * @Version 1.0
 **/
@Service
public class QueryServiceImpl implements QueryService {


    @Autowired
    private BaseOrganizationMapper baseOrganizationMapper;
    @Autowired
    private BaseCompanyMapper baseCompanyMapper;

    @Autowired
    private GridCompanyMapper gridCompanyMapper;

    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;

    @Autowired
    private ZoneBaseMapper zoneBaseMapper;

    @Autowired
    private BusiMenuClassifyMapper busiMenuClassifyMapper;

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Autowired
    private BusiIngredientMenuMapper busiIngredientMenuMapper;

    @Autowired
    private BusiCouponAuditingMapper busiCouponAuditingMapper;

    @Autowired
    private BaseIndexArticleMapper baseIndexArticleMapper;

    @Autowired
    private BusiActivityRuleMapper busiActivityRuleMapper;

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 功能描述: 代理公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @Override
    public Response agent(CompanyNameCondition condition) {
        logger.info("拼装分页信息......页码" + condition.getPageNum() + ",每页最大数" + condition.getPageSize());
        Page<AgentCompanyBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        agentUserMapper.selectCommon(condition);
        return Response.success(page.getResult(), page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 网格公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @Override
    public Response company(CompanyNameCondition condition) {
        logger.info("拼装分页信息......页码" + condition.getPageNum() + ",每页最大数" + condition.getPageSize());
        Page<GridCompanyBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        baseCompanyMapper.selectCommon(condition);
        return Response.success(page.getResult(), page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 网格公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @Override
    public Response grid(CompanyNameCondition condition) {
        logger.info("拼装分页信息......页码" + condition.getPageNum() + ",每页最大数" + condition.getPageSize());
        Page<GridCompanyBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        gridCompanyMapper.selectCommon(condition);
        return Response.success(page.getResult(), page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 网格公司实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @Override
    public Response allMachine(CompanyNameCondition condition) {
        logger.info("拼装分页信息......页码" + condition.getPageNum() + ",每页最大数" + condition.getPageSize());
        Page<GridCompanyBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        baseMachineInfoMapper.selectCommon(condition);
        return Response.success(page.getResult(), page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 片区实时检索接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 10:00
     */
    @Override
    public Response allZone(CompanyNameCondition condition) {
        logger.info("拼装分页信息......页码" + condition.getPageNum() + ",每页最大数" + condition.getPageSize());
        Page<GridCompanyBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        zoneBaseMapper.selectCommon(condition);
        return Response.success(page.getResult(), page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    @Override
    public Response queryAllClassify() {
        List<AllMenuClassifyBo> all = busiMenuClassifyMapper.selectAll();
        return Response.success(all);
    }

    /**
     * 功能描述: 优惠券
     *
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    @Override
    public Response allDiscounts() {
        List<BusiCouponAuditingEntry> all = busiCouponAuditingMapper.all();
        return Response.success(all);
    }

    /**
     * 功能描述: 饮品名
     *
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    @Override
    public Response allMenu() {
        return Response.success(busiIngredientMenuMapper.selectAll());
    }

    /**
     * 功能描述: 组织架构
     *
     * @return Response
     * @author cwz
     * @date 2019/1/5 9:49
     */
    @Override
    public Response allOrganization() {
        List<BaseOrganizationBo> list = new ArrayList<>();
        Map map = new HashMap(2);
        map.put("orgCode", "000");
        map.put("level", 0);
        BaseOrganizationBo baseOrganizationBos = baseOrganizationMapper.SelectListBo(map);
        list.add(baseOrganizationBos);
        return Response.success(list);
    }

    /**
     * 功能描述:
     *
     * @param
     * @return
     * @author cwz
     * @date 2019/2/26 15:46
     */
    @Override
    public Response allArticleType() {
        return Response.success(baseIndexArticleMapper.selectAllArticleType());
    }


    /**
     * 查询地图可视范围内的所有设备坐标
     *
     * @param condition 地图可视范围内的geohash
     * @return Response
     */
    @Override
    public Response queryAllTag(MapGeohashTagCondition condition) {
        if (null == condition.getGeohashs() || condition.getGeohashs().isEmpty()) {
            return Response.failure("参数不能为空");
        }
        return Response.success(baseMachineInfoMapper.queryAllTag(condition.getGeohashs()));
    }

    /**
     * 查询所有的活动规则
     *
     * @return
     */
    @Override
    public Response queryAllActivityRule() {
        return Response.success(busiActivityRuleMapper.selectAllActivityRule());
    }
}
