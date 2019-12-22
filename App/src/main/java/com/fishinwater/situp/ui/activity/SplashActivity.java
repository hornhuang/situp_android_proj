package com.fishinwater.situp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;

/**
 * url https://android.jlelse.eu/right-way-to-create-splash-screen-on-android-e7f1709ba154
 *
 * @author fishinwater-1999
 * @version 2019-12-18
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String user_id = SharedPreferencesUtil.getString(this, SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID);

        if (user_id == null || user_id.length() == 0) {
            ARouter.getInstance().build(RouteUtils.LogActivity).navigation();
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }

        finish();
    }
}
