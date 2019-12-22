package com.fishinwater.base.ui.base;

import android.app.Activity;
import android.content.Context;

import androidx.databinding.BaseObservable;

/**
 * @author fishinwater-1999
 * @date :2019/12/12 19:05
 */
public class BaseCustomViewModel extends BaseObservable {

    public Activity mContext;

    public BaseCustomViewModel(Activity activity) {
        this.mContext = activity;
    }

}
