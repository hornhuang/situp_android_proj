package com.fishinwater.login.model;

import com.fishinwater.login.ui.fragment.IOnResultListener;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBaseLog<L, R> {

    /**
     * 登录 Api
     * @param userAccount
     * @param mPassword
     * @param loginCallback
     */
    void login(String userAccount, String mPassword, L loginCallback);

    /**
     * 注册 Api
     * @param userAccount
     * @param mPassword
     * @param resistCallback
     */
    void resist(String userAccount, String mPassword, R resistCallback);

}
