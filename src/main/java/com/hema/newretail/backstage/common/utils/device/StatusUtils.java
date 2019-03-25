package com.hema.newretail.backstage.common.utils.device;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.model.device.sold.RedisSoldStatusBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName StatusUtils
 * @Description 机器设备实时状态
 * @Author ---CWZ
 * @Date 2018/12/13 14:12
 * @Version 1.0
 **/

@Component
public class StatusUtils {

    @Autowired
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private static final String MACHINESTATUS ="machineStatus";
    private static final String STATUSDESC ="statusDesc";
    private static final String STATUSTIME ="statusTime";
    private static final Integer TWELVE =12;
    private static final Integer ONE =1;
    private static final String ZEROS ="0";
    private static final String ONES ="1";
    private static final String TWOS ="2";
    private static final String THREES ="3";
    private static final String FOURS ="4";
    private static final String FIVES ="5";
    private static final String SIXS ="6";


    /**
     *
     * 功能描述: 机器设备实时状态
     *
     * @param  uuid
     * @param  isDelete
     * @param  fromType
     * @return  Map<String,String>
     * @author  cwz
     * @date  2018/12/13 14:14
     */
    public RedisSoldStatusBo deviceStatusRedis(String uuid , String isDelete , String fromType){

        RedisSoldStatusBo bo = new RedisSoldStatusBo();

        if(null == fromType){
            bo.setMachineState(FIVES);
        }else if(fromType.equals(ZEROS)){
            bo.setMachineState(FIVES);
        }else{
            if(isDelete.equals(ONES)){
                bo.setMachineState(FOURS);
            }else if(null == isDelete){
                bo.setMachineState(null);
            }else{
                if(uuid == null){
                    bo.setMachineState(null);
                }else {
                String status = redisUtils.hget(uuid,MACHINESTATUS,TWELVE);
                if (status ==null){
                    bo.setMachineState(SIXS);
                }else {
                    bo.setMachineState(status);
                }


                }
            }
        }


        if(uuid == null){
            bo.setStateDesc(null);
        }else {
        String statusDesc = redisUtils.hget(uuid,STATUSDESC,TWELVE);
        bo.setStateDesc(statusDesc);
        }
        if(uuid == null) {
            bo.setDuration(null);
        }else {
            String time = redisUtils.hget(uuid, STATUSTIME, TWELVE);
            if (time == null) {
                bo.setDuration(time);
            } else {
                String statusTime = TimeUtil.timeQuantum(System.currentTimeMillis() - Long.valueOf(time));
                bo.setDuration(statusTime);
            }
        }
        return  bo;
    }
}
