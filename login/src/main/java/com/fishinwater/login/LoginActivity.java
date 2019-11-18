package com.fishinwater.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.annotations.BindPath;

/**
 * App 模块是不能打包的
 *
 * 所以赚到 gradle.properties
 *
 */
@Route(path = "/test/1")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
