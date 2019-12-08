package com.fishinwater.situp.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author fishinwater-1999
 * @version 2019-12-05
 */
public class BaseActivity extends AppCompatActivity {

    public String TAG ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
    }
}
