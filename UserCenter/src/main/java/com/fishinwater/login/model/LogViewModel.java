package com.fishinwater.login.model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.fishinwater.base.common.ResponseUtil;
import com.fishinwater.base.common.UrlUtil;
import com.fishinwater.base.data.protocol.User;
import com.fishinwater.login.JsonUtils;
import com.fishinwater.login.ui.fragment.IOnResultListener;
import com.fishinwater.base.OkHttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogViewModel extends ViewModel implements IBaseLog {

    private final String TAG = "LogViewModel";

    @Override
    public void login(String userAccount, String mPassword, final IOnResultListener logResultListener) {
        OkHttpUtils.post()
                .url(UrlUtil.LOGIN_URL)
                .addParams("username", userAccount)
                .addParams("password", mPassword)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        logResultListener.onFailed(e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, response);
                        if (response.contains(ResponseUtil.JSON_SIMPLE)) {
                            logResultListener.onSucceed(response);
                        }else if (response.contains(ResponseUtil.WRONG_NAME)){
                            logResultListener.onFailed(new Exception("用户不存在"));
                        } else {
                            logResultListener.onFailed(new Exception("密码错误"));
                        }
                    }
                });
    }

    @Override
    public void resist(String userAccount, String mPassword, final IOnResultListener logResultListener) {
        OkHttpUtils.post()
                .url(UrlUtil.RESIST_URL)
                .addParams("username", userAccount)
                .addParams("password", mPassword)
                .build()
                .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                logResultListener.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d(TAG, "---" + response + "---");
                if (response.contains(ResponseUtil.WRONG_NAME)){
                    logResultListener.onSucceed("用户名已存在,请登录");
                } else if (response.contains(ResponseUtil.SUCCEED)) {
                    logResultListener.onSucceed(response);
                } else {
                    logResultListener.onFailed(new Exception("注册失败"));
                }
            }
        });
    }

    @Override
    public void logout(IOnResultListener logResultListener) {

    }

}
