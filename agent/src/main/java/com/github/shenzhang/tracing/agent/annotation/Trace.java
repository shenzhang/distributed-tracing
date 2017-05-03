package com.github.shenzhang.tracing.agent.annotation;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:45 PM.
 */
public @interface Trace {
    String value() default "";
}
