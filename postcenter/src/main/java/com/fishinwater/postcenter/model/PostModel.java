package com.fishinwater.postcenter.model;

import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.postcenter.callback.PostCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostModel {

    public void post(String user_id, String title, String content, String day_date, final PostCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.PUBLISH_POST)
                .addParams("user_id", user_id)
                .addParams("post_title", title)
                .addParams("post_content", content)
                .addParams("post_date", day_date)
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

    public void get(String post_id, final StringCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.GET_POST_BY_ID)
                .addParams("post_id", post_id)
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

    public void getAllPosts(int page, final StringCallback callback) {
        OkHttpUtils.post()
                .url(ApiUtils.GET_ALL_POSTS)
                .addParams("page", page + "")
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
