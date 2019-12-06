package com.fishinwater.plan.fragment.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fishinwater.plan.fragment.presenter.IBasePresenter;
import com.fishinwater.plan.fragment.presenter.Presenter;
import com.fishinwater.plan.fragment.viewmodel.PlanViewModel;

/**
 * @author fishinwater-1999
 * @version 2019-11-16
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment {

    T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unBind();
    }

    /**
     * 在这里绑定 Presenter
     */
    protected abstract void onBind();

    /**
     * 跟 Presenter 解绑
     */
    protected abstract void unBind();

    public void toast(String content){
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public void log(String TAG, String content){
        Log.d(TAG, content);
    }

    public T getPresenter(){
        return presenter;
    }

}

