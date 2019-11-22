package com.fishinwater.login.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public interface IBaseLogActivity {

    /**
     * 切换当前碎片
     * @param fragment
     */
    void replaceFragment(@NonNull Fragment fragment);

//    /**
//     * 用户登录
//     * @param userName
//     * @param userPassword
//     */
//    void login(@NonNull String userName, @NonNull String userPassword);
//
//    /**
//     * 用户登出
//     */
//    void logout();

}
