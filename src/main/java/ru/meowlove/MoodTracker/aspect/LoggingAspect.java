package ru.meowlove.MoodTracker.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Before("execution(* ru.meowlove.MoodTracker.services.*.*(..))")
    public void logServiceMethodCall(JoinPoint joinPoint) {
        log.info(
                "Method {} called with arguments {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(pointcut = "execution(* ru.meowlove.MoodTracker.services.*.*(..))", returning = "returnedValue")
    public void logServiceMethodReturn(JoinPoint joinPoint, Object returnedValue) {
        log.info(
                "Method {} returned {}",
                joinPoint.getSignature().getName(),
                returnedValue
        );
    }

    @AfterThrowing(pointcut = "execution(* ru.meowlove.MoodTracker.services.*.*(..))", throwing = "thrownException")
    public void logServiceMethodThrow(JoinPoint joinPoint, Exception thrownException) {
        log.info(
                "Method {} threw exception {}",
                joinPoint.getSignature().getName(),
                thrownException.getMessage()
        );
    }
}
