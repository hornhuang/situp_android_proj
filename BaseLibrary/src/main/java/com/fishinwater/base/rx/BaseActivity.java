package com.fishinwater.base.rx;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.fishinwater.base.R;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * toolbar 布局
     * 返回
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * toolbar 布局
     * 按钮功能
     * @param view
     */
    public abstract void action(View view);

}
