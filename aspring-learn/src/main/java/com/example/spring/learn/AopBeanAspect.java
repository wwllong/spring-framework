package com.example.spring.learn;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jack.wen
 * @since 2023/12/2 22:13
 */
@Component
@Aspect
public class AopBeanAspect {

    @Pointcut("execution(* com.example.spring.learn.AopBean.*(..))")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before method ......");
    }

}
