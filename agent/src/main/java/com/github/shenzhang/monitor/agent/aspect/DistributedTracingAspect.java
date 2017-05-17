package com.github.shenzhang.monitor.agent.aspect;

import com.github.shenzhang.monitor.agent.domain.Span;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:36 PM.
 */

@Aspect
@Component
public class DistributedTracingAspect {
    @Autowired
    private TracingRepository repository;

    @Around("@annotation(com.github.shenzhang.monitor.agent.annotation.DistributedTracing)")
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Span span = new Span();
        span.setBeginTime(begin);

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            span.setSuccess(false);
            throw throwable;
        } finally {
            long end = System.currentTimeMillis();
            span.setEndTime(end);
            span.setDuration(end - begin);

            repository.add(span);
        }
    }
}
