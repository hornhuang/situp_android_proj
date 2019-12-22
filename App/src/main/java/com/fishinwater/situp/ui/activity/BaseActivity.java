package com.fishinwater.situp.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.DateUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;

/**
 * @author fishinwater-1999
 * @version 2019-12-05
 */
public class BaseActivity extends AppCompatActivity implements IBaseCallback<String> {

    public String TAG ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
    }

    @Override
    public void onSucceed(String response) {
        Toast.makeText(this, response + "开启新的一天", Toast.LENGTH_SHORT).show();
        SharedPreferencesUtil.putString(this, SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.CURRENT_DAY, DateUtils.getDayDateStr());
    }

    @Override
    public void failed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
}
