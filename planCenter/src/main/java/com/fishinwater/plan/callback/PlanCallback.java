package com.fishinwater.plan.callback;

import com.zhy.http.okhttp.callback.StringCallback;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public interface PlanCallback<T> {

    void onSucceed(T... response);

    void onFailed(String err);

}
