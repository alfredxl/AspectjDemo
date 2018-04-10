package com.aspectj.aspectjaplay.activitylife;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> @author:      谢文良
 * <br> Date:        2018/4/4 16:48
 */
@Aspect
public class ActivityLife {
    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnCreate * *(..))")
    public void ActivityOnCreate() {
    }

    @Around("ActivityOnCreate()")
    public void ActivityOnCreate(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }

    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnRestart * *(..))")
    public void ActivityOnRestart() {
    }

    @Around("ActivityOnRestart()")
    public void ActivityOnRestart(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }

    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnStart * *(..))")
    public void ActivityOnStart() {
    }

    @Around("ActivityOnStart()")
    public void ActivityOnStart(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }

    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnResume * *(..))")
    public void ActivityOnResume() {
    }

    @Around("ActivityOnResume()")
    public void ActivityOnResume(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }

    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnPause * *(..))")
    public void ActivityOnPause() {
    }

    @Around("ActivityOnPause()")
    public void ActivityOnPause(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }

    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnStop * *(..))")
    public void ActivityOnStop() {
    }

    @Around("ActivityOnStop()")
    public void ActivityOnStop(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }

    @Pointcut("execution(@com.aspectj.aspectjaplay.activitylife.ActivityOnDestroy * *(..))")
    public void ActivityOnDestroy() {
    }

    @Around("ActivityOnDestroy()")
    public void ActivityOnDestroy(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String key = joinPoint.getSignature().getName();
        Log.d("aopLog", "execution_onActivityMethodAround: " + joinPoint.getThis().getClass().getSimpleName() + "_" + key + ":" + (endTime - startTime));
    }
}
