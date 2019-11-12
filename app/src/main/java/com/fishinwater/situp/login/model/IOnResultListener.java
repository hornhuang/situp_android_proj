package com.fishinwater.situp.login.model;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IOnResultListener {

    void onSucceed();

    void onFailed(Exception error);

    void onNameWrong();

    void onPasswordWrong();

}
