package com.aspectj.aspectjaplay.other;

import android.util.Log;
import android.widget.TextView;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> @author:      谢文良
 * <br> Date:        2018/4/9 10:34
 */
@Aspect
public class OtherJpoint {

    @Before("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    public void onViewClickListener(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            Object object = joinPoint.getArgs()[0];
            if (object instanceof TextView) {
                Log.d("aopLog", "clickViewName: " + ((TextView) object).getText().toString());
            }
        }
    }

    @Before("execution(com.aspectj.demo.bean.Person.new(..))")
    public void onStartPageConstructor(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "execution_Constructor_this:" + joinPoint.getThis().toString());
    }

    @Before("call(* com.aspectj.demo.StartPage.setButtonOneData(..))")
    public void onCallStartPagesetButtonOneData(JoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            Object object = joinPoint.getArgs()[0];
            Log.d("aopLog", "button_text: " + object.toString());
        }
    }

    @Before("staticinitialization(com.aspectj.demo.bean.Person)")
    public void onStartPageStaticinitialization(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "staticinitialization_this:" + joinPoint.getStaticPart().toString());
    }

    @Before("get(* com.aspectj.demo.bean.Person.*)")
    public void getPersonField(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "getPersonField:" + joinPoint.getStaticPart().toString());
    }

    @Before("set(* com.aspectj.demo.bean.Person.*)")
    public void setPersonField(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "setPersonField:" + joinPoint.getStaticPart().toString());
    }

    @Before("handler(java.lang.IllegalArgumentException)")
    public void handlerIllegalArgumentException(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "handlerIllegalArgumentException:" + joinPoint.getStaticPart().toString());
    }

    @Pointcut("within(com.aspectj.demo.StartPage)")
    public void withinStartPage() {
    }

    @Pointcut("call(* android.widget.Toast.show(..))")
    public void showToast() {
    }

    @Before("withinStartPage() && showToast()")
    public void withinStartPageAndShowToast(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "withinStartPageAndShowToast:" + joinPoint.getStaticPart().toString());
    }

    @Pointcut("withincode(* com.aspectj.demo.StartPage.showToast(..))")
    public void inStartPageShowToast() {
    }

    @Before("inStartPageShowToast() && showToast()")
    public void inStartPageShowToastAndShowToast(JoinPoint joinPoint) throws Throwable {
        Log.d("aopLog", "inStartPageShowToastAndShowToast: " + joinPoint.getSignature().getName());
    }
}
