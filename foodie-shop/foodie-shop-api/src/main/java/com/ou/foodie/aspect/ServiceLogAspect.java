package com.ou.foodie.aspect;

import com.ou.foodie.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/*
1. 第一个* 指的使任何的返回参数
2. 包名代表aop监控的类所在的包
3. ..代表该包以及其子包下的所有类方法
4. 第四处*代表类名，*代表所有类
5. *(..)*代表所有方法 ,(..)代表方法中的任何参数
 */
@Component
@Aspect
@Slf4j
public class ServiceLogAspect {
    @Around("execution(* com.ou.foodie.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint point) throws Throwable {
        log.info("===开始执行 {}.{}",point.getTarget().getClass(),point.getSignature().getName());
        long begin=System.currentTimeMillis();
        Object result = point.proceed();
        long end=System.currentTimeMillis();
        long takeTime=end-begin;
        if(takeTime>3000){
            log.error("======执行结束耗时{}毫秒",takeTime);
        }else if(takeTime>2500){
            log.warn("======执行结束耗时{}毫秒",takeTime);
        }else {
            log.info("======执行结束耗时{}毫秒",takeTime);
        }
        return result;
    }


}
