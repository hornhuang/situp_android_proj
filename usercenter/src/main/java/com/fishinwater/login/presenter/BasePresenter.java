package com.fishinwater.login.presenter;

import androidx.annotation.NonNull;

import com.fishinwater.login.ui.fragment.IOnResultListener;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public abstract class BasePresenter<V> implements IBasePresenter<V> {

    private V view;

    @Override
    public void attachView(@NonNull V mLogView) {
        view = mLogView;
    }

    @Override
    public void detachView() {
        if (view != null) {
            view = null;
        }
    }

}
