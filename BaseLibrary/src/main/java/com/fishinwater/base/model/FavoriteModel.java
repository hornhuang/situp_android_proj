package com.fishinwater.base.model;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.ApiUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-18
 */
public class FavoriteModel {

    public void favorite(String user_id, String post_id, final IBaseCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.ADD_FAVORITE)
                .addParams("post_id", post_id)
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

    public void judgeFavorite(String user_id, String post_id, final IBaseCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.JUDGE_FAVORITE)
                .addParams("post_id", post_id)
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
}
