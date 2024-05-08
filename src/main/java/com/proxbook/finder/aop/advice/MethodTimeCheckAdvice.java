package com.proxbook.finder.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class MethodTimeCheckAdvice {
    @Around("@annotation(com.proxbook.finder.aop.annotation.MethodTimeChecker)")
    public Object timeChecker(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object ret = joinPoint.proceed();
        stopWatch.stop();
        log.debug("[method Time Checker] {} time : {}",
                joinPoint.getSignature().toShortString(),stopWatch.getTotalTimeMillis());
        return ret;
    }

}
