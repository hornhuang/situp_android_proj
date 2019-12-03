package com.fishinwater.plan.util;

/**
 * @author fishinwater-1999
 * @version 2019-12-04
 */
public final class CallerClassGetter extends SecurityManager
{
    private static final CallerClassGetter INSTANCE = new CallerClassGetter();
    private CallerClassGetter() {}

    public static Class<?> getCallerClass() {
        return INSTANCE.getClassContext()[1];
    }
}
