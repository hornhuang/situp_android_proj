package com.fishinwater.plan.callback;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public interface PlanCallback<T> {

    void onSucceed(T... response);

    void onFailed(String err);

}
