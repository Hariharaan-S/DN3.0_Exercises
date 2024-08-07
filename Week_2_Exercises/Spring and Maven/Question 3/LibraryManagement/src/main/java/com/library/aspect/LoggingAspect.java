/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.aspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author shari
 */
@Slf4j
@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.library.librarymanagement.LibraryManagement.*.*(..))")
    public void loggingPointcut(){};
    
    @Before("loggingPointcut()")
    public void beforePointcut(JoinPoint joinpoint){
        log.info("Before method invoked: " + joinpoint.getSignature());
    }
    
    @After("loggingPointcut()")
    public void afterPointcut(JoinPoint joinpoint){
        log.info("After method invoked: " + joinpoint.getSignature());
    }
}
