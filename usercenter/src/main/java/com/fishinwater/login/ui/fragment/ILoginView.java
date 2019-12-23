package com.fishinwater.login.ui.fragment;

/**
 * @author fishinwater-1999
 * @version 2019-12-23
 */
public interface ILoginView {

    enum ErrCode {
        /**
         * 用户名错误
         */
        WRONG_USER_NAME,
        /**
         * 密码错误
         */
        WRONG_USER_PWD,
        /**
         * 网络连接错误
         */
        WRONG_NET_WORK
    }

    /**
     * 获得用户名
     * @return
     */
    String getUserName();

    /**
     *
     * 获得用户密码
     * @return
     */
    String getUserPwd();

    /**
     * 成功回调
     * @param response 要显示的信息
     */
    void showLoginSuccess(String response);

    /**
     * 失败回调
     * @param errCode 失败原因 NAME / PASSWORD / NET
     */
    void showLoginFailed(ErrCode errCode);

}
