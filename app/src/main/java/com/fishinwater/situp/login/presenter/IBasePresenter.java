package com.fishinwater.situp.login.presenter;

import com.fishinwater.situp.login.fragment.IBaseFragment;
import com.fishinwater.situp.login.model.IOnResultListener;
import com.fishinwater.situp.login.view.IBaseLogActivity;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBasePresenter<V extends IBaseFragment> {

    /**
     * 绑定
     * @param mLogView
     */
    void attachView(V mLogView);

    void detachView();

    void resister(IOnResultListener resultListener);

    void login(String userName, String userPassword, IOnResultListener resultListener);

    V getLoginVew();

}
