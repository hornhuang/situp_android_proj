package com.fishinwater.situp.model.viewmodel;

import android.app.Activity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class MineViewModel extends BaseObservable {

    String user_name;

    String user_introduction;

    private Activity mActivity;

    public MineViewModel(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Bindable
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Bindable
    public String getUser_introduction() {
        return user_introduction;
    }

    public void setUser_introduction(String user_introduction) {
        this.user_introduction = user_introduction;
    }
}
