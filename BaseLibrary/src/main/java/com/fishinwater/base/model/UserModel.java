package com.fishinwater.base.model;

import android.app.Application;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.data.protocol.User;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class UserModel {

    public void getData(String userId, IBaseCallback<User> callback) {
        callback.failed("UserModel");
    }

}
