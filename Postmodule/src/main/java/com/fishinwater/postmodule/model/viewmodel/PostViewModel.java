package com.fishinwater.postmodule.model.viewmodel;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.fishinwater.postmodule.callback.PostCallback;
import com.fishinwater.postmodule.model.PostModel;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostViewModel extends BaseObservable {

    private PostModel model;

    private String title;

    private String content;

    private Application application;

    public PostViewModel(Application application) {
        this.model = new PostModel();
        this.application = application;
    }

    public void post(View view) {
        model.post(title, content, new PostCallback() {
            @Override
            public void onSucceed(Object obj) {

            }

            @Override
            public void failed(String err) {
                Toast.makeText(application, err, Toast.LENGTH_SHORT).show();
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
