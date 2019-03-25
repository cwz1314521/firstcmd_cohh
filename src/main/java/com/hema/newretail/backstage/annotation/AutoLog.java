package com.hema.newretail.backstage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.annotation
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-23 10:42
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLog {

    /**
     * 是否启用，默认启用
     *
     * @return
     */
    boolean value() default true;

    /**
     * 日志内容，默认为空字符串
     *
     * @return
     */
    String logmsg() default "";
}
