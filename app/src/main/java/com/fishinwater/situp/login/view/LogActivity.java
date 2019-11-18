package com.fishinwater.situp.login.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fishinwater.situp.R;
import com.fishinwater.situp.login.fragment.LoginFragment;

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
