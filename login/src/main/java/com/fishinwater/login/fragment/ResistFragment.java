package com.fishinwater.login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fishinwater.login.R;
import com.fishinwater.login.model.IBaseLog;
import com.fishinwater.login.model.LogViewModel;
import com.fishinwater.login.presenter.IBasePresenter;
import com.fishinwater.login.presenter.LogPresenter;

/**
 * 注册界面
 *
 * @author fishinwater-1999
 */
public class ResistFragment extends BaseFragment implements IOnResultListener {

    private IBaseLog mViewModel;

    EditText mAccountEdit;

    EditText mPasswordEdit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resist_fragment, container, false);
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
        if (mViewModel == null) {

            mViewModel = new LogViewModel();
        }
    }

    @Override
    public IOnResultListener createView() {
        return this;
    }

    @Override
    public IBasePresenter createProsenter() {
        if (mViewModel == null) {
            mViewModel = new LogViewModel();
        }
        return new LogPresenter(mViewModel);
    }

    @Override
    public void onSucceed(String response) {
        Toast.makeText(getActivity(), response+"注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailed(Exception error) {
        Toast.makeText(getActivity(), "失败，原因：" + error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNameWrong() {
        Toast.makeText(getActivity(), "用户名错误", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPasswordWrong() {
        Toast.makeText(getActivity(), "密码错误", Toast.LENGTH_LONG).show();
    }

    public void resist(View v) {
        getPresenter().resister(mAccountEdit.getText().toString(), mPasswordEdit.getText().toString(), this);
    }

    @Override
    public void onDestroy() {
        onDetach();
        super.onDestroy();
    }
}
