package com.hema.newretail.backstage.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.user.BusiComplaintListCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.common.utils.excel.DeviceCashExcelUtils;
import com.hema.newretail.backstage.dao.BusiComplaintMapper;
import com.hema.newretail.backstage.service.user.BusiComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName BusiComplaintServiceImpl
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/23 11:36
 * @Version 1.0
 **/

@Service
public class BusiComplaintServiceImpl implements BusiComplaintService {


    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private final static String EMPTY ="";
    private static final String SDFSIX = "yyyy-MM-dd";

    @Autowired
    private BusiComplaintMapper busiComplaintMapper;

    @Value(value = "${excelModel.busiComplaintExcelName}")
    private String busiComplaintExcelName;

    /**
     * 功能描述:客诉记录列表
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/3/23 11:09
     */
    @Override
    public Response list(BusiComplaintListCondition condition) throws Exception{
        if(condition.getStartGmt() != null && !EMPTY.equals(condition.getStartGmt())){
            /*时间前有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                /*时间前后都有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }else {
                /*时间前有后没有*/
                condition.setEndGmts(new Date());
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }
        }else {
            /*时间前没有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                condition.setStartGmts(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndGmt(), SDFSIX)));
                /*时间前没有后有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }
        }
        Page<Map> maps = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        busiComplaintMapper.list(condition);
        return Response.success(maps.getResult(),maps.getTotal(),condition.getPageSize(),condition.getPageNum());
    }

    /**
     * 功能描述:客诉记录导出
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/3/23 11:09
     */
    @Override
    public Response excel(BusiComplaintListCondition condition ,HttpServletResponse response) throws Exception{
        if(condition.getStartGmt() != null && !EMPTY.equals(condition.getStartGmt())){
            /*时间前有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                /*时间前后都有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }else {
                /*时间前有后没有*/
                condition.setEndGmts(new Date());
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }
        }else {
            /*时间前没有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                condition.setStartGmts(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndGmt(), SDFSIX)));
                /*时间前没有后有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }
        }
        List<Map> list = busiComplaintMapper.list(condition);
        logger.info("设置ContentType......");
        response.setContentType("application/octet-stream");
        logger.info("设置文件名......");
        StringBuilder fileName = new StringBuilder();
        fileName.append(new Date()).append(".xlsx");
        String time = TimeUtil.getStringByDate(new Date());
        logger.info("设置Header......");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName.toString(), "utf-8"));
        response.flushBuffer();
        logger.info("调用AgentExcelUtils拼装类......");
        DeviceCashExcelUtils.busiComplaintExcel(busiComplaintExcelName,list,response.getOutputStream(),time);
        return Response.success();
    }

    /**
     * 功能描述:客诉记录编辑
     *
     * @param condition
     * @return
     * @author cwz
     * @date 2019/3/23 11:09
     */
    @Override
    public Response edit(BusiComplaintListCondition condition) {
        int remark = busiComplaintMapper.remark(condition);
        if(remark == 1){
            return Response.success();
        }else {
            return Response.failure();
        }

    }
}
