package com.hema.newretail.backstage.service.device.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.device.outstore.*;
import com.hema.newretail.backstage.common.utils.*;
import com.hema.newretail.backstage.common.utils.device.StatusUtils;
import com.hema.newretail.backstage.common.utils.excel.DeviceOutStoreExcelUtils;
import com.hema.newretail.backstage.dao.BaseMachineInfoMapper;
import com.hema.newretail.backstage.dao.BaseMachineOutStockOrderDetailMapper;
import com.hema.newretail.backstage.dao.BaseMachineOutStockOrderMapper;
import com.hema.newretail.backstage.entry.device.BaseMachineOutStockOrderDetailEntry;
import com.hema.newretail.backstage.entry.device.BaseMachineOutStockOrderEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.model.device.outsotre.*;
import com.hema.newretail.backstage.service.device.DeviceOutStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName DeviceManagementServiceImpl
 * @Description 设备管理 impl
 * @Author ---CWZ
 * @Date 2018/10/11 14:01
 * @Version 1.0
 **/
@Service
public class DeviceOutStoreServiceImpl implements DeviceOutStoreService {



    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private static final String EMPTY = "";
    private static final String TYPE = "型号";
    private static final String NUM = "台";
    private static final String COMMA = ",";
    private static final String PERIOD = ".";
    private static final Long ZERO = 0L;
    private static final String SDFEIGHT = "yyyy-MM-dd";
    private static final String SDFALL = "yyyy-MM-dd";
    private static final String SUCCESS = "success";
    @Value(value = "${excelModel.deviceOutStoreName}")
    private String deviceOutStoreName;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BaseMachineOutStockOrderMapper baseMachineOutStockOrderMapper;
    @Autowired
    private BaseMachineOutStockOrderDetailMapper baseMachineOutStockOrderDetailMapper;
    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;
    @Autowired
    private StatusUtils statusUtils;
    /**
     * 功能描述: 设备出库列表接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     * @throws  Exception
     */
    @Override
    public Response list(DeviceOutStoreCondition condition) throws Exception{
        DeviceOutStoreDBCondition convert = convert(condition);
        Page<DeviceOutListBo> page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        baseMachineOutStockOrderMapper.selectDeviceOutStoreMap(convert);
        return Response.success(page.getResult(),page.getTotal(),condition.getPageSize(),page.getPageNum());
    }

    /**
     * 功能描述: 设备出库添加接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response add(HttpServletRequest request, DeviceOutStoreAddCondition condition) throws Exception {

        BaseMachineOutStockOrderEntry entry = new BaseMachineOutStockOrderEntry();
        entry.setOutstockOrderCode(NumberUtils.deviceOutStore(baseMachineOutStockOrderMapper.selectOutCodeCount(NumberUtils.deviceOutStore(null))));
        entry.setAgentCompanyId(condition.getAgentCompanyId());
        entry.setOutstockTime(new Date());
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (userinfoJson == null) {
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        String user = String.valueOf(jsStr.get("userName"));
        entry.setOutstockUserName(user);
        entry.setReceiver(condition.getReceiver());
        entry.setRemark(condition.getRemark());
        entry.setOutstockUserId(condition.getUserId());
        int insert = baseMachineOutStockOrderMapper.insert(entry);
        if (insert == 1) {
            logger.info("存储成功" + entry.toString());
        } else {
            logger.error("存储本条失败" + entry.toString());
            return Response.failure("存储本条数据失败");
        }
        int success = 0;
        for (DeviceOutStoreAddDetailCondition d : condition.getDetails()
        ) {
            BaseMachineOutStockOrderDetailEntry detailEntry = new BaseMachineOutStockOrderDetailEntry();
            int count1 = baseMachineOutStockOrderDetailMapper.selectCountByMS(d.getMachineSequence());
            if (count1 > 0) {
                logger.error("设备<" + d.getMachineSequence() + ">已经出库");
                throw new Exception("无有效的设备序列号");
            } else {
                detailEntry.setMachineSequence(d.getMachineSequence());
                detailEntry.setMachineType(d.getMachineType());
                detailEntry.setMachineUuid(null);
                detailEntry.setOutstockId(entry.getId());
                detailEntry.setScanTime(TimeUtil.stringToDate(d.getScanTime(), "yyyy-MM-dd HH:mm:ss"));
                detailEntry.setScanPeople(d.getScanPeople());
                int insert1 = baseMachineOutStockOrderDetailMapper.insert(detailEntry);
                if (insert1 == 1) {
                    logger.info("存储成功" + detailEntry.toString());
                } else {
                    logger.error("存储本条失败" + detailEntry.toString());
                }
                success++;
            }

        }
        if (success > 0) {
            return Response.success();
        }
        return Response.failure("出库失败");
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
    public Response info(CommonIdCondition condition) {
        logger.info("查询主表");
        DeviceOutInfoBo deviceOutInfoBo = baseMachineOutStockOrderMapper.selectDeviceOutStoreInfoMap(condition.getId());
        if(deviceOutInfoBo == null){
            logger.error("没有查询到该条数据");
            return Response.failure("暂无数据");
        }
        List<DeviceOutListInfoDetailBo> details = deviceOutInfoBo.getDetails();
        for (DeviceOutListInfoDetailBo b :details
                ) {
            String machineState = statusUtils.deviceStatusRedis(b.getMachineUuid(), b.getIsDelete(), b.getFromType()).getMachineState();
            if(machineState !=null){
            b.setMachineState(Integer.parseInt(machineState));
            }
        }
        deviceOutInfoBo.setDetails(details);

        return Response.success(deviceOutInfoBo);
    }

    /**
     * 功能描述: 设备出库列表导出接口
     *
     * @param condition
     * @param response
     * @return Response
     * @throws Exception
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response export(DeviceOutStoreCondition condition,HttpServletResponse response) throws Exception {
        DeviceOutStoreDBCondition convert = convert(condition);
        System.out.println(convert);
        List<DeviceOutListBo> deviceOutListBos = baseMachineOutStockOrderMapper.selectDeviceOutStoreMap(convert);
        List<DeviceOutListExcelBo> deviceOutListExcelBos = new ArrayList<>();
        for (DeviceOutListBo d:deviceOutListBos
             ) {
            if(d == null ){
                continue;
            }
            DeviceOutListExcelBo bo = new DeviceOutListExcelBo();
            bo.setId(d.getId());
            bo.setAgentCompany(d.getAgentCompany());
            bo.setAgentContactWay(d.getAgentContactWay());
            bo.setOutstockOrderCode(d.getOutstockOrderCode());
            bo.setOutstockTime(d.getOutstockTime());
            bo.setReceiver(d.getReceiver());
            bo.setRemark(d.getRemark());
            StringBuffer details = new StringBuffer();
            for (DeviceOutListDetailBo detailBo:d.getDetails()
                 ) {
                details.append(detailBo.getMachineType()).append(TYPE).append(detailBo.getMachineNum()).append(NUM).append(COMMA);
            }
            if(!EMPTY.equals(details.toString()) && details != null){
                details.deleteCharAt(details.length() - 1).append(PERIOD);
                bo.setDetails(details.toString());
            }else{
                bo.setDetails(null);
            }

            deviceOutListExcelBos.add(bo);
        }
        response.setContentType("application/octet-stream");

        StringBuilder fileName = new StringBuilder();
        StringBuilder time = new StringBuilder();
        time.append("(").append(TimeUtil.getStringByDate(new Date())).append(")");
        fileName.append("设备出库单数据导出(").append(TimeUtil.getStringByDate(new Date())).append(").xlsx");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName.toString(), "utf-8"));
        response.flushBuffer();
        String result = DeviceOutStoreExcelUtils.exportExcel(deviceOutStoreName,deviceOutListExcelBos,response.getOutputStream(),time.toString());
        if(SUCCESS.equals(result)){
            return Response.success();
        }
        return Response.failure("导出失败");
    }

    /**
     *
     * 功能描述: 设备出库列表参数处理类
     *
     * @param  condition
     * @return  DeviceOutStoreDBCondition
     * @author  cwz
     * @date  2018/12/11 14:51
     */
    private DeviceOutStoreDBCondition convert(DeviceOutStoreCondition condition)throws Exception{
        DeviceOutStoreDBCondition device = new DeviceOutStoreDBCondition();
        device.setOrgCode(condition.getOrgCode());
        if(condition.getAgentCompany() != null && !ZERO.equals(condition.getAgentCompany())){
            device.setAgentCompany(condition.getAgentCompany());
        }
        if(condition.getMachineType() != null &&  !EMPTY.equals(condition.getMachineType())){
            device.setMachineType(condition.getMachineType());
        }
        if(condition.getOutstockOrderCode() != null && !EMPTY.equals(condition.getOutstockOrderCode())){
            device.setOutstockOrderCode(condition.getOutstockOrderCode());
        }
        if(condition.getOutstockTimeStart() != null && !EMPTY.equals(condition.getOutstockTimeStart())){
            /*时间前有*/
            if(condition.getOutstockTimeEnd() != null && !EMPTY.equals(condition.getOutstockTimeEnd())){
                /*时间前后都有*/
                device.setOutstockTimeEnd(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getOutstockTimeEnd(), SDFEIGHT)));
                device.setOutstockTimeStart(TimeUtil.stringToDate(condition.getOutstockTimeStart(), SDFEIGHT));
            }else {
                /*时间前有后没有*/
                device.setOutstockTimeStart(TimeUtil.stringToDate(condition.getOutstockTimeStart(), SDFEIGHT));
                device.setOutstockTimeEnd(new Date());
            }
        }else {
            /*时间前没有*/
            if(condition.getOutstockTimeEnd() != null && !EMPTY.equals(condition.getOutstockTimeEnd())){
                /*时间前没有后有*/
                device.setOutstockTimeStart(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getOutstockTimeEnd(), SDFEIGHT)));
                device.setOutstockTimeEnd(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getOutstockTimeEnd(), SDFEIGHT)));
            }


        }
        if(condition.getIds() != null && !EMPTY.equals(condition.getIds())){
            List<Long> longs = StringUtil.stringsToLong(condition.getIds());
            device.setIds(longs);
        }
        return device;
}



}