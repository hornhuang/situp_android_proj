package com.fishinwater.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生命周期：源码期 class
 *         运行期 RUNTIME
 *         源码期 SOURCE
 * @author fishinwater-1999
 * @version 2019-11-18
 */
// 申明注解的作用域 TYPE - 类
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface BindPath {

    /**
     * 接收一个 String 的值
     */
    String value();
}
