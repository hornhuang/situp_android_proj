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
public class LoginFragment extends BaseFragment<ILoginView, LogPresenter> implements ILoginView {

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
        getPresenter().login(
                mAccountEdit.getText().toString(),
                mPasswordEdit.getText().toString(),
                this);
    }

    @Override
    public ILoginView createView() {
        return this;
    }

    @Override
    public LogPresenter createProsenter() {
        if (mLogViewModel == null) {
            mLogViewModel = new LogViewModel();
        }
        return new LogPresenter(mLogViewModel);
    }

    @Override
    public void onDestroy() {
        onDetach();
        super.onDestroy();
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getUserPwd() {
        return null;
    }

    @Override
    public void showLoginSuccess(String response) {
        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_LONG).show();
        SharedPreferencesUtil.putString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID, response);
        ARouter.getInstance().build(RouteUtils.MainActivity).navigation();
        getActivity().finish();
    }

    @Override
    public void showLoginFailed(ErrCode errCode) {
        if (errCode == ErrCode.WRONG_USER_NAME) {
            Toast.makeText(getActivity(), "用户名错误", Toast.LENGTH_LONG).show();
        }else if (errCode == ErrCode.WRONG_USER_PWD){
            Toast.makeText(getActivity(), "密码错误", Toast.LENGTH_LONG).show();
        }else if (errCode == ErrCode.WRONG_NET_WORK) {
            Toast.makeText(getActivity(), "未知，请检查网络", Toast.LENGTH_LONG).show();
        }
    }
}
