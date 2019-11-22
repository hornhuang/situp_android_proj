package com.fishinwater.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.login.R;
import com.fishinwater.login.fragment.LoginFragment;

/**
 * 登录注册功能
 *
 * @author fishinwater-1999
 */
@Route(path = "/login/login")
public class LogActivity extends AppCompatActivity implements IBaseLogActivity {

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
}
