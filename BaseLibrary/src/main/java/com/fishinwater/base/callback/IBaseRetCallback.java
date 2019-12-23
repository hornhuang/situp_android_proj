package com.fishinwater.base.callback;

import retrofit2.Response;

/**
 * @author fishinwater-1999
 * @version 2019-12-23
 */
public interface IBaseRetCallback<T> {

    void onSucceed(Response<T> response);

    void onFailed(Throwable t);

}
