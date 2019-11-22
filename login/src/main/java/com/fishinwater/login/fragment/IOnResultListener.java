package com.fishinwater.login.fragment;

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
