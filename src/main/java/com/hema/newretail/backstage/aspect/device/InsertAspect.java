package com.hema.newretail.backstage.aspect.device;


import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.BaseMachineTypeMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *@Department 新零售
 *@ClassName InsertAspect
 *@Description 设备类型添加时处理名称重复的问题
 *@Author ---CWZ
 *@Date 2019/1/22 13:06
 *@Version 1.0
 **/
@Aspect
@Order(1)
public class InsertAspect {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private BaseMachineTypeMapper baseMachineTypeMapper;
    private static final String ADD = "add";
    private static final String EDIT = "edit";

    @Pointcut("@annotation(com.hema.newretail.backstage.annotation.RepeatMachineTypeName)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint jp)throws Throwable{
        logger.info("进入切面");
        Object[] args = jp.getArgs();
        String mothod = jp.getSignature().getName();
        Object arg = args[1];
        Map<String, Object> map = new HashMap<>(7);
                 Class<?> clazz = arg.getClass();
                 System.out.println(clazz);
                 for (Field field : clazz.getDeclaredFields()) {
                         field.setAccessible(true);
                         String fieldName = field.getName();
                        Object value = field.get(arg);
                         map.put(fieldName, value);
                     }
        String machineTypeName = map.get("machineTypeName").toString();
        String machineTypeCode = map.get("machineTypeCode").toString();
                 Map<String,Object> mmap = new HashMap<>(3);
                 mmap.put("machineTypeName",machineTypeName);
                 mmap.put("machineTypeCode",machineTypeCode);
        if(mothod.equals(ADD)){
            int num = baseMachineTypeMapper.countName(mmap);
            if(num > 0){
                logger.info("操作失败，名称或型号重复,结束切面");
                return Response.failure("操作失败，名称或型号重复");
            }else {
                logger.info("结束切面");
                return jp.proceed();
            }
        }
        if(mothod.equals(EDIT)){
            mmap.put("id",map.get("id"));
            int num = baseMachineTypeMapper.countName(mmap);
            if(num > 0){
                logger.info("操作失败，名称或型号重复,结束切面");
                return Response.failure("操作失败，名称或型号重复");
            }else {
                logger.info("结束切面");
                return jp.proceed();
            }
        }
        logger.info("结束切面");
        Object proceed = jp.proceed();
        return proceed;
    }


    @AfterReturning("pointCut()")
    public void afterReturning(){ }
    @AfterThrowing("pointCut()")
    public void afterThrowing(){ }
    @Before("pointCut()")
    public void before(){ }
    @After("pointCut()")
    public void after(){ }
}
