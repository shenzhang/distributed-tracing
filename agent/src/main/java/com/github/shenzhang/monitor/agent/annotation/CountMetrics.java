package com.github.shenzhang.monitor.agent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:45 PM.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountMetrics {
    String value() default "";

    boolean count() default true;

    boolean time() default false;

    boolean status() default false;
}
