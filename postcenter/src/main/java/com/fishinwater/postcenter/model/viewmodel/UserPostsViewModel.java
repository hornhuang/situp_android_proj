package com.fishinwater.postcenter.model.viewmodel;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.fishinwater.base.callback.MyListCallback;
import com.fishinwater.base.common.DateUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.postcenter.model.UserPostsModel;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-12
 */
public class UserPostsViewModel extends PostBean {

    private UserPostsModel model;

    private Activity mContext;

    public UserPostsViewModel(Activity mContext) {
        this.model = new UserPostsModel();
        this.mContext = mContext;
    }

    public void post(String user_id, final MyListCallback callback) {
        model.post(user_id,
                new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callback.onFailed(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callback.onSucceed(JSONUtils.jsonStrtoList(PostBean.class, response));
                    }

                });
    }
}
