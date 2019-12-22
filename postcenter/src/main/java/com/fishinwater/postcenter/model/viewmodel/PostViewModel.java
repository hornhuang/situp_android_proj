package com.fishinwater.postcenter.model.viewmodel;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.fishinwater.base.common.DateUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.base.ui.adapter.ProfileViewModel;
import com.fishinwater.base.ui.base.BaseCustomViewModel;
import com.fishinwater.postcenter.callback.PostCallback;
import com.fishinwater.postcenter.model.PostModel;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostViewModel extends BaseCustomViewModel {

    private PostModel model;

    private ProfileViewModel profileViewModel;

    private PostBean postBean;

    private UserBean userBean;

    public PostViewModel(Activity mContext) {
        super(mContext);
        this.model = new PostModel();
        this.profileViewModel = new ProfileViewModel();
        this.postBean = new PostBean();
        this.userBean = new UserBean();
    }

    public void post(View view) {
        model.post(SharedPreferencesUtil.getString(mContext, SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID),
                postBean.getPost_title(),
                postBean.getPost_content(),
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
    public PostBean getPostBean() {
        if (postBean == null) {
            postBean = new PostBean();
        }
        return postBean;
    }

    public void setPostBean(PostBean postBean) {
        this.postBean = postBean;
        notifyPropertyChanged(BR.postBean);
    }

    @Bindable
    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
        profileViewModel.setImageUrl(userBean.getUser_head_img());
        notifyPropertyChanged(BR.userBean);
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
