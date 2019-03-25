package com.hema.newretail.backstage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Department 新零售
 * @ClassName RepeatMachineTypeName
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/1/22 16:06
 * @Version 1.0
 **/


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface RepeatMachineTypeName {

    String value() default "";
}
