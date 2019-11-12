package com.fishinwater.situp.login.presenter;

import com.fishinwater.situp.login.fragment.IBaseFragment;
import com.fishinwater.situp.login.model.IOnResultListener;
import com.fishinwater.situp.login.view.IBaseLogActivity;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public abstract class BasePresenter<V extends IBaseFragment> implements IBasePresenter<V>{

    private V view;

    @Override
    public void attachView(V mLogView) {
        this.view = mLogView;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void resister(IOnResultListener resultListener) {

    }

    @Override
    public void login(String userName, String userPassword, IOnResultListener resultListener) {

    }

    @Override
    public V getLoginVew() {
        return this.view;
    }

}
