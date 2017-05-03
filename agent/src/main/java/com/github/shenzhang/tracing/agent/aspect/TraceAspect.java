package com.github.shenzhang.tracing.agent.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:36 PM.
 */

@Aspect
@Component
public class TraceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceAspect.class);

    @Around("@annotation(com.github.shenzhang.tracing.agent.annotation.Trace)")
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long end = System.nanoTime();

            log(joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName(), end - begin);
        }
    }

    private void log(String name, long duration) {
        LOGGER.info("{}: {} ns", name, duration);
    }
}
