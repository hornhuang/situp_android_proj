package com.fishinwater.situp.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.fishinwater.situp.R;
import com.fishinwater.situp.login.factory.Factory;
import com.fishinwater.situp.login.fragment.BaseFragment;
import com.fishinwater.situp.login.fragment.LoginFragment;
import com.fishinwater.situp.login.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * 登录注册功能
 *
 * @author fishinwater-1999
 */
public class LogActivity extends AppCompatActivity implements IBaseLogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);
        replaceFragment((Fragment) Factory.getFragmentInstance("loginfragment"));
    }

    @Override
    public void replaceFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit();
    }
}
