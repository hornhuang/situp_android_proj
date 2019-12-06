package com.fishinwater.plan.callback;

import java.util.Collection;
import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-06
 */
public interface PlansCallBack<T> {
    /**
     * 成功回调
     * @param list
     */
    void onSucceed(List<T> list);

    /**
     * 失败回调
     * @param errMessage
     */
    void onFailed(String errMessage);

}
