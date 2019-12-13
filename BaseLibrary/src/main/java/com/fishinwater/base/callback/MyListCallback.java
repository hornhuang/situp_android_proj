package com.fishinwater.base.callback;

import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public interface MyListCallback<T> {

    void onSucceed(List<T> list);

    void onFailed(String errMsg);

}
