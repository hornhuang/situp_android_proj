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
                //.url(ApiUtils.)
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

}
