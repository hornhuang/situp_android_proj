package com.fishinwater.login.presenter;

import com.fishinwater.login.fragment.IOnResultListener;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBasePresenter<V extends IOnResultListener> {

    /**
     * 绑定
     * @param mLogView
     */
    void attachView(V mLogView);

    void detachView();

    void resister(String userName, String userPassword, IOnResultListener resultListener);

    void login(String userName, String userPassword, IOnResultListener resultListener);

    V getLoginVew();

}
