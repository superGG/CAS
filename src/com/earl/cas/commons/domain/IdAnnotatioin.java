package com.earl.cas.commons.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 添加该注解用于标志主建，id,用于动态更新
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target( { ElementType.FIELD})
public @interface IdAnnotatioin {  
	  
    String value() default "";  
}  