package com.fishinwater.base.callback;

import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-12-13
 */
public interface MyObjCallback<T> {

    void onSucceed(T list);

    void onFailed(String errMsg);
}
