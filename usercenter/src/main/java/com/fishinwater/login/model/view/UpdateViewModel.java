package com.fishinwater.login.model.view;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.base.ui.adapter.ProfileViewModel;

import java.lang.ref.WeakReference;

/**
 * @author fishinwater-1999
 * @version 2019-12-17
 */
public class UpdateViewModel extends BaseObservable {

    private String userName;

    private String userIntroduction;

    private String userPassword;

    private WeakReference<Activity> activityWeakReference;

    private UserModel model;

    private ProfileViewModel profileViewModel;

    public UpdateViewModel(Activity activity) {
        this.activityWeakReference = new WeakReference<>(activity);
        this.model = new UserModel();
        this.profileViewModel = new ProfileViewModel();
    }

    public void init(@NonNull final String user_id) {
        model.getData(user_id, new IBaseCallback<String>() {
                    @Override
                    public void onSucceed(String obj) {
                        UserBean userBean = JSONUtils.StringToObj(UserBean.class, obj);
                        setUserName(userBean.getUser_name());
                        setUserIntroduction(userBean.getUser_introduction());
                        profileViewModel.setImageUrl(userBean.getUser_head_img());
                    }

                    @Override
                    public void failed(String err) {
                        setUserName("请先登录");
                        setUserIntroduction("大家都在等你哦～");
                    }
                }
        );
    }

    private void update(@NonNull String user_id) {
        model.update(user_id, getUserName(), getUserPassword(), getUserIntroduction(), new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                UserBean userBean = JSONUtils.StringToObj(UserBean.class, obj);
                setUserName(userBean.getUser_name());
                setUserIntroduction(userBean.getUser_introduction());
            }

            @Override
            public void failed(String err) {
                Toast.makeText(activityWeakReference.get(), "更新失败了 OO", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Bindable
    public String getUserIntroduction() {

        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
        notifyPropertyChanged(BR.userIntroduction);
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
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }
    @Bindable
    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    public void setProfileViewModel(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
    }
}
