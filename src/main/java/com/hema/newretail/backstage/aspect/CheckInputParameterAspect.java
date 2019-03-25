package com.hema.newretail.backstage.aspect;

import com.alibaba.fastjson.JSON;
import com.hema.newretail.backstage.common.utils.IllegalRequestParamFilterUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.aspect
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-19 15:00
 */
@Component
@Aspect
public class CheckInputParameterAspect {
    private final Logger Logger = LoggerFactory.getLogger(getClass());

    private static final String IS_SQL_INJECTION = "输入参数存在SQL注入风险";
    private static final String UNVALIDATED_INPUT = "输入参数含有非法字符";

    @Pointcut("execution(* com.hema.newretail.backstage.controller..*(..))")
    public void params() {
    }

    @Around("params()")
    public Object doBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs();

        //序列化时过滤掉request和response
        List<Object> logArgs = Arrays.stream(args)
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)
                        && !(arg instanceof HttpSession) && !(arg instanceof BindingResult) && !(arg instanceof MultipartFile)))
                .collect(Collectors.toList());

        String jsonString = "";
        if (args.length > 0) {
            jsonString = JSON.toJSONString(logArgs);
            Logger.info("jsonString:{}", jsonString);
        }
        if (!IllegalRequestParamFilterUtil.paramFilter(jsonString)) {
            Logger.info(IS_SQL_INJECTION);
            throw new RuntimeException(UNVALIDATED_INPUT);
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Logger.info("当前调用接口-[" + request.getRequestURL() + "]");
        return result;
    }
}
