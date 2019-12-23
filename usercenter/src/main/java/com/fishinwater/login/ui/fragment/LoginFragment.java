package com.fishinwater.login.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.base.callback.UserMgrService;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.login.R;
import com.fishinwater.login.databinding.LoginFragmentBinding;
import com.fishinwater.login.model.LogViewModel;
import com.fishinwater.login.presenter.IBasePresenter;
import com.fishinwater.login.presenter.LogPresenter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fishinwater-1999
 */
public class LoginFragment extends BaseFragment implements IOnResultListener {

    private static final String TAG = "LoginFragment";

    EditText mAccountEdit;

    EditText mPasswordEdit;

    private LogViewModel mLogViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoginFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        View view = binding.getRoot();
        binding.setLogCallback(getLogActivity());
        binding.setFragment(this);
        iniView(view);
        return view;
    }

    private void iniView(View view) {
        mAccountEdit = view.findViewById(R.id.user_account);
        mPasswordEdit = view.findViewById(R.id.user_password);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLogViewModel == null) {
            mLogViewModel = new LogViewModel();
        }
    }

    public void login(View v) {
        // baseUrl() 设置路由地址
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 设置参数
        UserMgrService service = retrofit.create(UserMgrService.class);
        Call<UserBean> call = service.login( mAccountEdit.getText().toString(),
                mPasswordEdit.getText().toString());
        // 回调
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                Log.d(TAG, response.body().toString());
                LoginFragment.this.onSucceed(JSONUtils.objToString(response.body()));
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                // 失败时做处理
            }
        });


        // 发送同步请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response<UserBean> response = call.execute();
//                    Log.d("123123", "msg--" + response.code() + response.body());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        getPresenter().login(
//                mAccountEdit.getText().toString(),
//                mPasswordEdit.getText().toString(),
//                this);
    }

    @Override
    public IOnResultListener createView() {
        return this;
    }

    @Override
    public IBasePresenter createProsenter() {
        if (mLogViewModel == null) {
            mLogViewModel = new LogViewModel();
        }
        return new LogPresenter(mLogViewModel);
    }

    @Override
    public void onSucceed(String response) {
        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
        String user_id = JSONUtils.StringToObj(UserBean.class, response).getUser_id();
        SharedPreferencesUtil.putString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID, user_id);
        ARouter.getInstance().build(RouteUtils.MainActivity).navigation();
        getActivity().finish();
    }

    @Override
    public void onFailed(Exception error) {
        Toast.makeText(getActivity(), "onFailed" + error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNameWrong() {
        Toast.makeText(getActivity(), "onNameWrong", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPasswordWrong() {
        Toast.makeText(getActivity(), "onPasswordWrong", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        onDetach();
        super.onDestroy();
    }

}
