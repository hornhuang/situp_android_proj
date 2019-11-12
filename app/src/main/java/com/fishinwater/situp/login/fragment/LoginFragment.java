package com.fishinwater.situp.login.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.fishinwater.situp.R;
import com.fishinwater.situp.login.factory.Factory;
import com.fishinwater.situp.login.model.IOnResultListener;
import com.fishinwater.situp.login.model.LogViewModel;
import com.fishinwater.situp.login.presenter.BasePresenter;
import com.fishinwater.situp.login.presenter.IBasePresenter;
import com.fishinwater.situp.login.presenter.LogPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author fishinwater-1999
 */
public class LoginFragment extends BaseFragment implements IBaseFragment, IOnResultListener {

    @BindView(R.id.user_account)
    EditText mAccountEdit;

    @BindView(R.id.user_password)
    EditText mPasswordEdit;

    private LogViewModel mLogViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLogViewModel == null) {
            mLogViewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        }
    }

    @OnClick(R.id.login)
    public void login(View view) {
        login(mAccountEdit.getText().toString(), mPasswordEdit.getText().toString());
    }

    @Override
    public IBaseFragment createView() {
        return this;
    }

    @Override
    public IBasePresenter createProsenter() {
        if (mLogViewModel == null) {
            mLogViewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        }
        return new LogPresenter(mLogViewModel);
    }

    @Override
    public void login(String userName, String userPassword) {
        getPresenter().login(userName, userPassword, this);
    }

    @Override
    public void resist(String userName, String userPassword) {

    }

    @Override
    public void onSucceed() {
        Toast.makeText(getActivity(), "succeed", Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void onFailed(Exception error) {
        Toast.makeText(getActivity(), "onFailed", Toast.LENGTH_LONG).show();
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
