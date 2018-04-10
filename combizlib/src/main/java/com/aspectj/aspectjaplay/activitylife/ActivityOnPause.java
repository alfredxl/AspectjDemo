package com.aspectj.aspectjaplay.activitylife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <br> ClassName:   ${className}
 * <br> Description:
 * <br>
 * <br> @author:      谢文良
 * <br> Date:        2018/4/4 16:42
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface ActivityOnPause {
}
