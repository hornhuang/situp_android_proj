package com.fishinwater.situp.login.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fishinwater.situp.login.presenter.BasePresenter;
import com.fishinwater.situp.login.presenter.IBasePresenter;
import com.fishinwater.situp.login.view.IBaseLogActivity;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public abstract class BaseFragment<V extends IBaseFragment, P extends IBasePresenter<V>> extends Fragment implements IBaseLogActivity {

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

    @Override
    public void replaceFragment(@NonNull Fragment fragment) {

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
