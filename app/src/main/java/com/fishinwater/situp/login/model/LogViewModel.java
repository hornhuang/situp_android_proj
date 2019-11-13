package com.fishinwater.situp.login.model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.fishinwater.situp.util.OkHttpUtil;

import okhttp3.OkHttpClient;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogViewModel extends ViewModel implements IBaseLog{

    private final String TAG = "LogViewModel";

    @Override
    public void login(String userAccount, String mPassword, IOnResultListener logResultListener) {
        Log.d(TAG, "name=" + userAccount + "password" + userAccount);
        try {
            OkHttpUtil.uploadStringByPOST("https://baidu.com", "", new OkHttpClient());
        }catch (Exception e) {
            e.printStackTrace();
        }
        logResultListener.onSucceed();
    }

    @Override
    public void logout(IOnResultListener logResultListener) {

    }

    @Override
    public boolean isLogged() {
        return false;
    }

}
