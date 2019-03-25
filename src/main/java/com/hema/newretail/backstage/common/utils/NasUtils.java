package com.hema.newretail.backstage.common.utils;

import com.aliyuncs.batchcompute.main.v20151111.*;
import com.aliyuncs.batchcompute.model.v20151111.*;
import com.aliyuncs.batchcompute.pojo.v20151111.NASConfig;
import com.aliyuncs.exceptions.ClientException;
import jdk.nashorn.internal.runtime.linker.NashornBeansLinker;

/**
 * @Department 新零售
 * @ClassName NasUtils
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/12/5 11:43
 * @Version 1.0
 **/
public class NasUtils {

    static String ACCESS_KEY_ID = "xxx";  //这里填写您的 AccessKeyId
    static String ACCESS_KEY_SECRET = "xxx"; //这里填写您的 AccessKeySecret
    static String REGION_ID = "cn-xxx";   //这里填写 region
    static String jobId = "job-000000005BE3E897000007FA00114EE9";
    public static void main(String[] args) {
        BatchCompute client = new BatchComputeClient(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try{
            ChangeJobPriorityResponse response = client.changeJobPriority(jobId, 2);
            //成功
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println("StatusCode: " + response.getStatusCode());
        }catch(ClientException e){
            e.printStackTrace();
            //失败
        }
    }
}
