package com.fishinwater.base.model;

import androidx.annotation.NonNull;

import com.fishinwater.base.OkHttpUtil;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.ApiUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-19
 */
public class PictureModel {

    public void getPicturesByFlag(@NonNull String typeFlag, final IBaseCallback<String> callback) {
        OkHttpUtils.post()
                .url(ApiUtils.GET_PICTURE)
                .addParams("icon_flag", typeFlag)
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
