package com.fishinwater.login.model;

import com.fishinwater.login.fragment.IOnResultListener;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBaseLog {

    void login(String userAccount, String mPassword, IOnResultListener logResultListener);

    void resist(String userAccount, String mPassword, IOnResultListener logResultListener);

    void logout(IOnResultListener logResultListener);

}
