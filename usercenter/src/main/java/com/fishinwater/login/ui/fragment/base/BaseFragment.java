package com.fishinwater.login.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.fragment.app.Fragment;

import com.fishinwater.login.callback.ILogCallback;
import com.fishinwater.login.presenter.IBasePresenter;
import com.fishinwater.login.ui.activity.LogActivity;

import java.lang.ref.WeakReference;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public abstract class BaseFragment<V , P extends IBasePresenter<V>> extends Fragment {

    /**
     * View 层
     */
    private V mActivityView;

    /**
     * Presenter 层
     */
    private P mBaseResister;

    private LogActivity logActivity;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        logActivity = (LogActivity) context;
    }

    public LogActivity getLogActivity() {
        return logActivity;
    }

    public void setLogActivity(LogActivity logActivity) {
        this.logActivity = logActivity;
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
