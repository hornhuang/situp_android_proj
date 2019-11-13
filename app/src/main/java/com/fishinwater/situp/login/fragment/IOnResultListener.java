package com.fishinwater.situp.login.fragment;

import com.zhy.http.okhttp.callback.StringCallback;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IOnResultListener {

    void onSucceed(String response);

    void onFailed(Exception error);

    void onNameWrong();

    void onPasswordWrong();

}
