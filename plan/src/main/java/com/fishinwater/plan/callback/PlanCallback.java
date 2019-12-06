package com.fishinwater.plan.callback;

import java.util.Collection;

/**
 * @author fishinwater-1999
 * @version 2019-12-06
 */
public interface PlanCallback<T>  {
    /**
     * 成功回调
     * @param collection
     */
    void onSucceed(T collection);

    /**
     * 失败回调
     * @param errMessage
     */
    void onFailed(String errMessage);
}
