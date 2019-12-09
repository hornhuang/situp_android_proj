package com.fishinwater.postmodule.callback;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public interface PostCallback<T> {

    void onSucceed(T obj);

    void failed(String err);

}
