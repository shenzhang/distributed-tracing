package com.github.shenzhang.monitor.agent.aspect;

import com.github.shenzhang.monitor.agent.annotation.CountMetrics;
import com.github.shenzhang.monitor.agent.metrics.CountMetricsStore;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:36 PM.
 */

@Aspect
@Component
public class MetricsAspect {
    @Autowired
    private CountMetricsStore countMetricsStore;

    @Around("@annotation(com.github.shenzhang.monitor.agent.annotation.CountMetrics)")
    public Object count(ProceedingJoinPoint joinPoint) throws Throwable {
        CountMetrics annotation;

        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            annotation = signature.getMethod().getAnnotation(CountMetrics.class);

            if (annotation == null) {
                return joinPoint.proceed();
            }
        } catch (Exception e) {
            return joinPoint.proceed();
        }

        String name = annotation.value();

        if (annotation.count()) {
            countMetricsStore.inc(name + ".count");
        }

        long begin = System.currentTimeMillis();

        try {
            Object proceed = joinPoint.proceed();
            if (annotation.status()) {
                countMetricsStore.inc(name + ".success");
            }

            return proceed;
        } catch (Throwable throwable) {
            if (annotation.status()) {
                countMetricsStore.inc(name + ".failed");
            }
            throw throwable;
        } finally {
            if (annotation.time()) {
                long duration = System.currentTimeMillis() - begin;
                countMetricsStore.inc(name + ".time", duration);
            }
        }
    }
}
