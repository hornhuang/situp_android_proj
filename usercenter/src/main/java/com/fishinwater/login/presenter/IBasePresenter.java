package com.fishinwater.login.presenter;

import com.fishinwater.login.ui.fragment.IOnResultListener;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBasePresenter<V> {

    /**
     * 绑定
     * @param mLogView
     */
    void attachView(V mLogView);

    void detachView();

    void resister(String userName, String userPassword, V resultListener);

    void login(String userName, String userPassword, V resultListener);

    V getLoginVew();

}
