package com.fishinwater.postcenter.model.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.BaseViewModel;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.postcenter.model.PostModel;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostPageViewModel extends BaseViewModel {

    private String userName;

    private String userIntroduce;

    private String postTitle;

    private String postContent;

    private UserModel userModel;

    private PostModel postModel;

    public PostPageViewModel(Application application) {
        super(application);
        this.userModel = new UserModel();
        this.postModel = new PostModel();
    }

    public void getData(String postId) {
        postModel.get(postId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                PostBean postBean = JSONUtils.StringToObj(PostBean.class, response);
                setPostTitle(postBean.getPost_title());
                setPostContent(postBean.getPost_content());
            }
        });
    }

    private void getUser(String uId) {
        userModel.getData(uId, new IBaseCallback<UserBean>() {
            @Override
            public void onSucceed(UserBean obj) {

            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), "getUser", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserIntroduce() {
        return userIntroduce;
    }

    public void setUserIntroduce(String userIntroduce) {
        this.userIntroduce = userIntroduce;
        notifyPropertyChanged(BR.userIntroduce);
    }

    @Bindable
    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
        notifyPropertyChanged(BR.postTitle);
    }

    @Bindable
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
        notifyPropertyChanged(BR.postContent);
    }
}
