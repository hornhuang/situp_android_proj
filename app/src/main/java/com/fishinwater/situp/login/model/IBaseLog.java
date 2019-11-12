package com.fishinwater.situp.login.model;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBaseLog {

    void login( IOnResultListener logResultListener );

    void logout( IOnResultListener logResultListener );

    boolean isLogged();

}
