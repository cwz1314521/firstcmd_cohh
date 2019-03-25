package com.hema.newretail.backstage.common.validator;

import com.hema.newretail.backstage.common.utils.Response;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.config
 *
 * @author zhs
 * @link
 * @date 2018-08-31 9:50
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    /**
     * 拦截捕捉自定义异常
     *
     * @param ex 异常
     * @return 返回异常信息
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Response constraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return Response.failureValid(msgList);
    }

    /**
     * 数据校验异常
     *
     * @param ex MethodArgumentNotValidException
     * @return Response
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        List<String> msgList = new ArrayList<>();
        errors.forEach(error -> msgList.add(error.getDefaultMessage()));
        return Response.failureValid(msgList);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response runtimeExceptionHandler(RuntimeException ex) {
        return Response.failureValid(ex.getMessage());
    }

    /**
     * 数据校验异常
     *
     * @param ex
     * @return Response
     */
    @ExceptionHandler(value = Exception.class)
    public Response exceptionhandler(Exception ex) {
        String errors = ex.getMessage();
        System.out.println(errors);
        return Response.failureValid(errors);
    }
}
