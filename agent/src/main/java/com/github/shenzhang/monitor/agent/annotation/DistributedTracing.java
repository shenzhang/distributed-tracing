package com.github.shenzhang.monitor.agent.annotation;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:45 PM.
 */
public @interface DistributedTracing {
    String value() default "";
}
