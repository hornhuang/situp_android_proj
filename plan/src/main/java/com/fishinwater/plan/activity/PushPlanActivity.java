package com.fishinwater.plan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fishinwater.plan.R;
import com.fishinwater.plan.util.CallerClassGetter;

/**
 * @author fishinwater-1999
 */
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
