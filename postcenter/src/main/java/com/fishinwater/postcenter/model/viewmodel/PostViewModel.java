package com.fishinwater.postcenter.model.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.fishinwater.base.common.DateUtils;
import com.fishinwater.base.common.SharedPreferencesUtil;
import com.fishinwater.postcenter.callback.PostCallback;
import com.fishinwater.postcenter.model.PostModel;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostViewModel extends BaseObservable {

    private PostModel model;

    private String title;

    private String content;

    private Activity mContext;

    public PostViewModel(Activity mContext) {
        this.model = new PostModel();
        this.mContext = mContext;
    }

    public void post(View view) {
        model.post(SharedPreferencesUtil.getString(mContext, SharedPreferencesUtil.USER_KEY),
                title,
                content,
                DateUtils.getDayDateStr(),
                new PostCallback<String>() {

                    @Override
                    public void onSucceed(String obj) {
                        Toast.makeText(mContext, obj, Toast.LENGTH_SHORT).show();
                        mContext.finish();
                    }

                    @Override
                    public void failed(String err) {
                        Toast.makeText(mContext, err, Toast.LENGTH_SHORT).show();
                        mContext.finish();
                    }
                });
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
