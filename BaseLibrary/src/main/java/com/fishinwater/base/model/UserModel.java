package com.fishinwater.base.model;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.data.protocol.UserBean;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class UserModel {

    public void getData(String userId, IBaseCallback<UserBean> callback) {
        callback.failed("UserModel");
    }

}
