package com.fishinwater.base.model;

import com.fishinwater.base.OkHttpUtil;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.data.protocol.UserBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class UserModel {

    public void getData(String userId, final IBaseCallback<String> callback) {
        OkHttpUtils.post()
                .url(ApiUtils.GET_USER)
                .addParams("user_id", userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSucceed(response);
                    }
                });
    }

    public void update(String userId, String user_name, String user_intrduce, String user_password, final IBaseCallback<String> callback) {
        OkHttpUtils.post()
                .url(ApiUtils.UPDATE_USER)
                .addParams("user_id", userId)
                .addParams("user_name", user_name)
                .addParams("user_password", user_password)
                .addParams("user_introduction", user_intrduce)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSucceed(response);
                    }
                });
    }

    public void updateIntroduction(String userId, String user_intrduce, final IBaseCallback<String> callback) {
        OkHttpUtils.post()
                .url(ApiUtils.UPDATE_USER_INTRODUCE)
                .addParams("user_id", userId)
                .addParams("user_introduction", user_intrduce)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSucceed(response);
                    }
                });
    }

    public void updateHeadImg(String userId, String user_head_img, final IBaseCallback<String> callback) {
        OkHttpUtils.post()
                .url(ApiUtils.UPDATE_USER_IMG)
                .addParams("user_id", userId)
                .addParams("user_head_img", user_head_img)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.failed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSucceed(response);
                    }
                });
    }

}
