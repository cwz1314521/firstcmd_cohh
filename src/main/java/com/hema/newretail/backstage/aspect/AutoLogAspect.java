package com.hema.newretail.backstage.aspect;

import com.hema.newretail.backstage.annotation.AutoLog;
import com.hema.newretail.backstage.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.el.MethodNotFoundException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.aspect.device
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 10:44
 */
@Component
@Aspect
public class AutoLogAspect {
    private static final Class<AutoLog> AUTO_LOG_CLASS = AutoLog.class;
    private static final Class<Api> API_CLASS = Api.class;
    private static final Class<ApiOperation> API_OPERATION_CLASS = ApiOperation.class;
    private static final String SEPARATOR = "-";
    private static Logger OPERATE_LOG;
    private Method method;
    private Class<?> targetClass;
    private boolean debug;

    @Around(value = "@within(com.hema.newretail.backstage.annotation.AutoLog) || @annotation(com.hema.newretail.backstage.annotation.AutoLog)")
    public Object test(ProceedingJoinPoint point) throws Throwable {
        init(point);
        Object result = point.proceed();
        if (result instanceof Response) {
            Response r = (Response) result;
            if (r.getSuccess()) {
                printLog();
            }
        }
        return result;
    }

    private void init(ProceedingJoinPoint point) {
        Signature signature = point.getSignature();
        targetClass = point.getTarget().getClass();
        String loggerName = "OPERATE_LOG";
        OPERATE_LOG = LoggerFactory.getLogger(loggerName);
        method = Arrays.stream(targetClass.getDeclaredMethods())
                .filter(method1 -> method1.toString().equals(signature.toLongString()) || method1.toString().contains(signature.toLongString()))
                .findFirst().orElseThrow(MethodNotFoundException::new);
        debug = isDebug();
    }

    private void printLog() {
        if (debug) {
            OPERATE_LOG.info(getLogMsg());
        }
    }

    private boolean isDebug() {
        return (targetClass.isAnnotationPresent(AUTO_LOG_CLASS) && targetClass.getAnnotation(AUTO_LOG_CLASS).value())
                || (method.isAnnotationPresent(AUTO_LOG_CLASS) && method.getAnnotation(AUTO_LOG_CLASS).value());
    }

    /**
     * 获取日志信息
     *
     * @return 日志信息
     */
    private String getLogMsg() {
        String msg = "";
        if (method.isAnnotationPresent(AUTO_LOG_CLASS) && isNotEmpty(method.getAnnotation(AUTO_LOG_CLASS).logmsg())) {
            msg = method.getAnnotation(AUTO_LOG_CLASS).logmsg();
        } else if (targetClass.isAnnotationPresent(AUTO_LOG_CLASS) && isNotEmpty(targetClass.getAnnotation(AUTO_LOG_CLASS).logmsg())) {
            msg = targetClass.getAnnotation(AUTO_LOG_CLASS).logmsg();
        }
        // 从swagger取值
        if (isEmpty(msg)) {
            String swaggerMsg = "";
            if (targetClass.isAnnotationPresent(API_CLASS)) {
                if (isNotEmpty(targetClass.getAnnotation(API_CLASS).value())) {
                    swaggerMsg = swaggerMsg + targetClass.getAnnotation(API_CLASS).value() + SEPARATOR;
                } else if (isNotEmpty(targetClass.getAnnotation(API_CLASS).description())) {
                    swaggerMsg = swaggerMsg + targetClass.getAnnotation(API_CLASS).description() + SEPARATOR;
                }
            }
            if (method.isAnnotationPresent(API_OPERATION_CLASS) && isNotEmpty(method.getAnnotation(API_OPERATION_CLASS).value())) {
                swaggerMsg = swaggerMsg + method.getAnnotation(API_OPERATION_CLASS).value();
            }
            msg = swaggerMsg.endsWith(SEPARATOR) ? swaggerMsg.substring(0, swaggerMsg.lastIndexOf(SEPARATOR)) : swaggerMsg;
        }
        return msg;
    }

    private boolean isEmpty(String str) {
        return null == str || str.length() == 0;
    }

    private boolean isNotEmpty(String str) {
        return null != str && str.length() > 0;
    }
}
