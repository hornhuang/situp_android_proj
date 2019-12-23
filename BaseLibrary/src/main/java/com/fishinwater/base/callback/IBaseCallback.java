package com.fishinwater.base.callback;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
@Deprecated
public interface IBaseCallback<T> {

    void onSucceed(T obj);

    void failed(String err);
}
