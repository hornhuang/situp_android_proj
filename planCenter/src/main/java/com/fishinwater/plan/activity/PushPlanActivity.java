package com.fishinwater.plan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.ui.base.BaseActivity;
import com.fishinwater.plan.R;

/**
 * @author fishinwater-1999
 */
@Route(path = RouteUtils.PushPlanActivity)
public class PushPlanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_plan);
    }

    public static void actionStart(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, PushPlanActivity.class);
        activity.startActivity(intent);
    }
}
