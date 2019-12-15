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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fishinwater.base.common.SharedPreferencesUtil;
import com.fishinwater.login.R;
import com.fishinwater.login.databinding.LoginFragmentBinding;
import com.fishinwater.login.model.LogViewModel;
import com.fishinwater.login.presenter.IBasePresenter;
import com.fishinwater.login.presenter.LogPresenter;

import java.lang.ref.WeakReference;

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
        getPresenter().login(
                mAccountEdit.getText().toString(),
                mPasswordEdit.getText().toString(),
                this);
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
        SharedPreferencesUtil.putString(getActivity(), SharedPreferencesUtil.USER_KEY, response);
        getLogActivity().finish();
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
