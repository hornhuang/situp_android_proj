package com.fishinwater.login.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fishinwater.login.presenter.IBasePresenter;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public abstract class BaseFragment<V extends IOnResultListener, P extends IBasePresenter<V>> extends Fragment {

    /**
     * View 层
     */
    private V mActivityView;

    /**
     * Presenter 层
     */
    private P mBaseResister;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActivityView == null) {
            mActivityView = createView();
        }
        if (mBaseResister == null) {
            mBaseResister = createProsenter();
        }
    }

    public abstract V createView();

    public abstract P createProsenter();

    public P getPresenter() {
        if (mBaseResister == null) {
            createProsenter();
        }
        return mBaseResister;
    }

}
