package com.fishinwater.login.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fishinwater.login.presenter.IBasePresenter;
import com.fishinwater.login.ui.activity.LogActivity;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public abstract class BaseFragment<V , P extends IBasePresenter<V>> extends Fragment {

    /**
     * Presenter 层
     */
    private P mBaseResister;

    /**
     * 暴露 Activity 接口，用于绑定
     */
    private LogActivity logActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 自动绑定
        if (mBaseResister == null) {
            mBaseResister = createPresenter();
        }
    }

    /**
     * 跟 Activity 的接口绑定
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        logActivity = (LogActivity) context;
    }

    /**
     * 获得 Activity 接口
     * @return
     */
    public LogActivity getLogActivity() {
        return logActivity;
    }

    /**
     * 在这里确定要生成的 Presenter 对象类型
     * @return
     */
    public abstract P createPresenter();

    /**
     * 获得 Presenter 对象
     * @return
     */
    public P getPresenter() {
        if (mBaseResister == null) {
            createPresenter();
        }
        return mBaseResister;
    }

    /**
     * 碎片销毁时解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mBaseResister = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
