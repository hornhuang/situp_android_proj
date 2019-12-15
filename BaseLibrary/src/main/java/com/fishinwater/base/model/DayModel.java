package com.fishinwater.base.model;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.common.JSONUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-15
 */
public class DayModel {


    public void publishDay(String user_id, final IBaseCallback<String> callback) {
        OkHttpUtils.post()
                .url(ApiUtils.PUBLISH_DAY)
                .addParams("user_id", user_id)
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

    public void getDay(String user_id, String day_date, final StringCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.GET_DAY)
                .addParams("user_id", user_id)
                .addParams("day_date", day_date)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onError(call, e, id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onResponse(response, id);
                    }
                });
    }
}
