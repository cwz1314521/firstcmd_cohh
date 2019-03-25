package com.hema.newretail.backstage.service.device.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.device.outstore.CommonIdCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.AddressEditCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.DeviceSoldCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.SetMachineServiceCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.SetTaskCycleCondition;
import com.hema.newretail.backstage.common.utils.GeoHash;
import com.hema.newretail.backstage.common.utils.LocationBean;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.StringUtil;
import com.hema.newretail.backstage.common.utils.device.StatusUtils;
import com.hema.newretail.backstage.dao.BaseMachineInfoMapper;
import com.hema.newretail.backstage.dao.BaseMachineOutStockOrderMapper;
import com.hema.newretail.backstage.dao.GridCompanyGeohashMapper;
import com.hema.newretail.backstage.dao.RefZoneMachineMapper;
import com.hema.newretail.backstage.entry.BaseMachineInfoEntry;
import com.hema.newretail.backstage.model.device.sold.RedisSoldStatusBo;
import com.hema.newretail.backstage.model.device.sold.SoldDetailBo;
import com.hema.newretail.backstage.model.device.sold.SoldListBo;
import com.hema.newretail.backstage.service.device.DeviceSoldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName DeviceSoldServiceImpl
 * @Description 设备管理--已出售--Service
 * @Author ---CWZ
 * @Date 2018/12/12 20:42
 * @Version 1.0
 **/

@Service
public class DeviceSoldServiceImpl implements DeviceSoldService {


    @Autowired
    private BaseMachineOutStockOrderMapper baseMachineOutStockOrderMapper;

    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;

    @Autowired
    private RefZoneMachineMapper refZoneMachineMapper;

    @Autowired
    private StatusUtils statusUtils;

    @Autowired
    private GridCompanyGeohashMapper gridCompanyGeohashMapper;

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private static final String MACHINESTATUS = "machineStatus";
    private static final String STATUSDESC = "statusDesc";
    private static final String STATUSTIME = "statusTime";


    /**
     * 功能描述: 已出售设备列表接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response list(DeviceSoldCondition condition) {
        logger.info("拼装分页" + "num： " + condition.getPageNum() + "size： " + condition.getPageSize());
        DeviceSoldCondition condition1 = new DeviceSoldCondition();
        condition1.setPageNum(1);
        condition1.setPageSize(10000);
        List<SoldListBo> list1 = baseMachineOutStockOrderMapper.selectSoldListMap(condition1);
        Page<SoldListBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        baseMachineOutStockOrderMapper.selectSoldListMap(condition);
        List<SoldListBo> list = page.getResult();
        /**
         * 正常、1维护、2离线、3故障、4暂停服务、5未激活 6其他
         */
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;
        int zero = 0;
        Map<String, Object> map = new HashMap<>(6);
        for (SoldListBo bo : list1
        ) {
            String machineState = statusUtils.deviceStatusRedis(bo.getMachineUuid(), bo.getIsDelete(), bo.getFromType()).getMachineState();
            if (machineState != null) {
                bo.setMachineState(Integer.parseInt(machineState));
                logger.info("轮存状态-轮询redis :" + bo.getMachineState().toString());
                if (bo.getMachineState() == 1) {
                    one++;
                } else if (bo.getMachineState() == 2) {
                    two++;
                } else if (bo.getMachineState() == 3) {
                    three++;
                } else if (bo.getMachineState() == 4) {
                    four++;
                } else if (bo.getMachineState() == 5) {
                    five++;
                } else if (bo.getMachineState() == 0) {
                    zero++;
                } else if (bo.getMachineState() == 6) {
                    six++;
                }
            } else {
                bo.setMachineState(null);
            }

        }
        for (SoldListBo bo : list
        ) {
            String machineState = statusUtils.deviceStatusRedis(bo.getMachineUuid(), bo.getIsDelete(), bo.getFromType()).getMachineState();
            if (machineState != null) {
                bo.setMachineState(Integer.parseInt(machineState));
                logger.info("轮存状态-轮询redis :" + bo.getMachineState().toString());
            } else {
                bo.setMachineState(null);
            }
        }
        map.put("one", one);
        map.put("two", two);
        map.put("three", three);
        map.put("four", four);
        map.put("five", five);
        map.put("zero", zero);
        map.put("List", list);
        map.put("six",six);
        if (null != list && list.size() > 0) {
            return Response.success(map, page.getTotal(), condition.getPageSize(), condition.getPageNum());
        } else {
            return Response.success(list, page.getTotal(), condition.getPageSize(), condition.getPageNum());
        }
    }

    /**
     * 功能描述: 批量设置任务周期
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response setTaskCycle(SetTaskCycleCondition condition) {
        List<Long> ids = StringUtil.stringsToLong(condition.getIds());
        condition.setId(ids);
        logger.info("ids转化" + ids);
        List<Map<String, Object>> list = baseMachineOutStockOrderMapper.selectTaskCycleByIds(condition);
        int i = baseMachineOutStockOrderMapper.updateTaskPeriod(condition);
        if (i < 1) {
            logger.info("操作失败" + ids);
            return Response.failure("操作失败");
        }
        if (null != list && list.size() == i) {
            return Response.success(list);
        }
        return Response.success();
    }

    /**
     * 功能描述: 批量手动关停服务接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response setMachineService(SetMachineServiceCondition condition) {
        List<Long> ids = StringUtil.stringsToLong(condition.getIds());
        logger.info("ids转化" + ids);
        condition.setId(ids);
        int i = baseMachineOutStockOrderMapper.updateSetMachineService(condition);
        if (i < 1) {
            logger.info("操作失败" + ids);
            return Response.failure("操作失败");
        }
        return Response.success();
    }

    /**
     * 功能描述: 设备出库详情接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response detail(CommonIdCondition condition) {
        SoldDetailBo soldDetailBo = baseMachineOutStockOrderMapper.selectSoldListDetailMap(condition.getId());
        if (soldDetailBo == null) {
            logger.error("暂无数据" + condition.getId());
            return Response.failure("暂无数据");
        }
        RedisSoldStatusBo statusBo = statusUtils.deviceStatusRedis(soldDetailBo.getUuid(), soldDetailBo.getMachineIsDeleted(), soldDetailBo.getFromType());
        soldDetailBo.setMachineState(statusBo.getMachineState());
        soldDetailBo.setStateDesc(statusBo.getStateDesc());
        soldDetailBo.setDuration(statusBo.getDuration());
        soldDetailBo.setStateType(statusBo.getMachineState());
        return Response.success(soldDetailBo);
    }

    /**
     * 功能描述: 设备地址修改
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response addressEdit(AddressEditCondition condition) {
        BaseMachineInfoEntry entry = baseMachineInfoMapper.selectByPrimaryKey(condition.getBmiId());
        String preGeoHash = entry.getHashcode();
        GeoHash geoHash = new GeoHash(condition.getLatitude(), condition.getLongitude());
        String newGenHash = geoHash.getGeoHashBase32();
        Long preLong = refZoneMachineMapper.selectByGeoHash(preGeoHash);
        Long newLong = refZoneMachineMapper.selectByGeoHash(newGenHash);

        Long preGridLong = gridCompanyGeohashMapper.selectGridCompanyIdByHashcode(preGeoHash);
        Long newGridLong = gridCompanyGeohashMapper.selectGridCompanyIdByHashcode(newGenHash);

        if (preLong != null && preGridLong != null && preLong.equals(newLong) && preGridLong.equals(newGridLong)) {
            condition.setGeoHash(newGenHash);
            baseMachineInfoMapper.updateEditAddress(condition);
            return Response.success();
        } else {
            return Response.failure("新地址配料方案或网格公司冲突，无法保存");
        }
    }
}
