package com.fishinwater.login.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.login.R;
import com.fishinwater.login.callback.ILogCallback;
import com.fishinwater.login.ui.fragment.LoginFragment;
import com.fishinwater.login.ui.fragment.ResistFragment;

/**
 * 登录注册功能
 *
 * @author fishinwater-1999
 */
@Route(path = "/UserCenter/login")
public class LogActivity extends AppCompatActivity implements IBaseActivity, ILogCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        replaceFragment(new LoginFragment());
    }

    @Override
    public void replaceFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit();
    }

    @Override
    public void login(View view) {
        replaceFragment(new LoginFragment());
    }

    @Override
    public void resist(View view) {
        replaceFragment(new ResistFragment());
    }
}