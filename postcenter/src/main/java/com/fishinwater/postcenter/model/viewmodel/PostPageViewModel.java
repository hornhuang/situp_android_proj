package com.fishinwater.postcenter.model.viewmodel;

import android.app.Application;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.DrawableUtils;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.view.BaseViewModel;
import com.fishinwater.base.model.CollectModel;
import com.fishinwater.base.model.FavoriteModel;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.base.ui.adapter.ProfileViewModel;
import com.fishinwater.postcenter.model.PostModel;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostPageViewModel extends BaseViewModel {

    private String user_id;

    private String post_id;

    private String userName;

    private String userIntroduce;

    private String postTitle;

    private String postContent;

    private UserModel userModel;

    private PostModel postModel;

    private ImageView collectImg;

    private ImageView favoriteImg;

    private ProfileViewModel profileViewModel;

    public PostPageViewModel(Application application) {
        super(application);
        this.userModel = new UserModel();
        this.postModel = new PostModel();
        this.profileViewModel = new ProfileViewModel();
    }

    public void getData(final String postId) {
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

    public void getUser(String uId) {
        userModel.getData(uId, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                UserBean userBean = JSONUtils.StringToObj(UserBean.class, obj);
                setUserName(userBean.getUser_name());
                setUserIntroduce(userBean.getUser_introduction());
                profileViewModel.setImageUrl(userBean.getUser_head_img());
            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), "getUser", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void judgeCollect() {
        new CollectModel().judgeCollect(user_id, post_id, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                Toast.makeText(getApplication(), obj, Toast.LENGTH_SHORT).show();
                if (obj.contains("-")) {
                    Glide.with(getApplication())
                            .load(DrawableUtils.UN_COLLECT_PIC)
                            .into(collectImg);
                }else {
                    Glide.with(getApplication())
                            .load(DrawableUtils.IS_COLLECT_PIC)
                            .into(collectImg);
                }
            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), err, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void judgeFavorite() {
        new FavoriteModel().judgeFavorite(user_id, post_id, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                Toast.makeText(getApplication(), obj, Toast.LENGTH_SHORT).show();
                if (obj.contains("-")) {
                    Glide.with(getApplication())
                            .load(DrawableUtils.UN_FAVORITE_PIC)
                            .into(favoriteImg);
                }else {
                    Glide.with(getApplication())
                            .load(DrawableUtils.IS_FAVORITE_PIC)
                            .into(favoriteImg);
                }
            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), err, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void collect() {
        new CollectModel().collect(user_id, post_id, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                Toast.makeText(getApplication(), obj + "sdfsdfsdfsdfsdf", Toast.LENGTH_SHORT).show();
                judgeCollect();
            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), err, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void favorite() {
        new FavoriteModel().favorite(user_id, post_id, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                Toast.makeText(getApplication(), obj + "-----", Toast.LENGTH_SHORT).show();
                judgeFavorite();
            }

            @Override
            public void failed(String err) {
                Toast.makeText(getApplication(), err, Toast.LENGTH_SHORT).show();
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

    public void setCollectImg(ImageView collectImg) {
        this.collectImg = collectImg;
    }

    public void setFavoriteImg(ImageView favoriteImg) {
        this.favoriteImg = favoriteImg;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
    @Bindable
    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    public void setProfileViewModel(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        notifyPropertyChanged(BR.profileViewModel);
    }
}
