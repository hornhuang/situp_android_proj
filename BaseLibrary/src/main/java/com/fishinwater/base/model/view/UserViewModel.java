package com.fishinwater.base.model.view;

import android.app.Activity;

import com.fishinwater.base.data.protocol.UserBean;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public class UserViewModel extends UserBean {

    private Activity mActivity;

    public UserViewModel(Activity mActivity) {
        this.mActivity = mActivity;
    }
}
