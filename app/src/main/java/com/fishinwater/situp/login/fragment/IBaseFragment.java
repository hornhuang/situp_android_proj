package com.fishinwater.situp.login.fragment;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBaseFragment {

    /**
     * 登录
     * @param userName
     * @param userPassword
     */
    void login(String userName, String userPassword);

    /**
     * 注册
     * @param userName
     * @param userPassword
     */
    void resist(String userName, String userPassword);

}
