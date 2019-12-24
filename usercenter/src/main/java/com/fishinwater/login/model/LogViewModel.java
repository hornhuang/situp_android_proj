package com.fishinwater.login.model;

import androidx.lifecycle.ViewModel;

import com.fishinwater.base.callback.IBaseRetCallback;
import com.fishinwater.base.callback.UserMgrService;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.data.protocol.UserBean;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fishinwater-1999
 * @version 2019-11-12
 */
public class LogViewModel implements IBaseLog<IBaseRetCallback<UserBean>, IBaseRetCallback<String>> {

    private final String TAG = "LogViewModel";

    @Override
    public void login(String userAccount, String userPassword, final IBaseRetCallback<UserBean> retCallback) {
        // baseUrl() 设置路由地址
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 设置参数
        UserMgrService service = retrofit.create(UserMgrService.class);
        retrofit2.Call<UserBean> call = service.login( userAccount, userPassword);
        // 回调
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(retrofit2.Call<UserBean> call, Response<UserBean> response) {
                retCallback.onSucceed(response);
            }

            @Override
            public void onFailure(retrofit2.Call<UserBean> call, Throwable t) {
                // 失败时做处理
                retCallback.onFailed(t);
            }
        });
    }

    @Override
    public void resist(String userAccount, String mPassword, final IBaseRetCallback<String> retCallback) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserMgrService service = retrofit.create(UserMgrService.class);
        retrofit2.Call<String> call = service.resist(userAccount, mPassword);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                retCallback.onSucceed(response);
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                retCallback.onFailed(t);
            }
        });
    }

}
