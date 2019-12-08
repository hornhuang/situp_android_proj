package com.fishinwater.situp.model;

import com.fishinwater.situp.classes.base.UserBean;
import com.fishinwater.base.callback.MyStringCallback;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * @author fishinwater-1999
 * @version 2019-11-13
 */
public class UserImpl implements IUser{


    @Override
    public void login(UserBean userBean, StringCallback stringCallback) {

    }

    @Override
    public void resist(UserBean userBean, StringCallback stringCallback) {

    }

    @Override
    public void update(IUser user, MyStringCallback stringCallback) {

    }

    @Override
    public void delete(IUser user, MyStringCallback stringCallback) {

    }

    @Override
    public boolean isLogged() {
        return false;
    }
}
