package com.fishinwater.postmodule.model.viewmodel;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.data.protocol.User;
import com.fishinwater.base.model.BaseViewModel;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.postmodule.Post;
import com.fishinwater.postmodule.model.PostPageModel;

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

    private PostPageModel postPageModel;

    public PostPageViewModel(Application application) {
        super(application);
        this.userModel = new UserModel();
        this.postPageModel = new PostPageModel();
    }

    public void getData(String postId) {
        postPageModel.getData("", new IBaseCallback<Post>() {
            @Override
            public void onSucceed(Post obj) {
                getUser("");
            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), "PostPageViewModel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUser(String uId) {
        userModel.getData(uId, new IBaseCallback<User>() {
            @Override
            public void onSucceed(User obj) {

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
