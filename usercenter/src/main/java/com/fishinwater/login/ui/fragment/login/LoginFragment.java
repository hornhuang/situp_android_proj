package com.fishinwater.login.ui.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.login.R;
import com.fishinwater.login.databinding.LoginFragmentBinding;
import com.fishinwater.login.model.LogViewModel;
import com.fishinwater.login.presenter.LogPresenter;
import com.fishinwater.login.ui.fragment.ILoginView;
import com.fishinwater.login.ui.fragment.base.BaseFragment;

/**
 * @author fishinwater-1999
 */
public class LoginFragment extends BaseFragment<ILoginView, LogPresenter> implements ILoginView {

    private static final String TAG = "LoginFragment";

    private LogViewModel mLogViewModel;

    private LoginFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        View view = binding.getRoot();
        binding.setLogCallback(getLogActivity());
        binding.setFragment(this);
        return view;
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
                getUserName(),
                getUserPwd(),
                this);
    }

    @Override
    public LogPresenter createPresenter() {
        if (mLogViewModel == null) {
            mLogViewModel = new LogViewModel();
        }
        return new LogPresenter(mLogViewModel);
    }

    @Override
    public String getUserName() {
        return binding.userAccount.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return binding.userPassword.getText().toString();
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
