package com.fishinwater.login.model;

import android.app.Activity;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.base.ui.adapter.ProfileViewModel;

public class MineViewModel extends BaseObservable {

    String user_name;

    String user_introduction;

    ProfileViewModel profileViewModel;

    private Activity mActivity;

    private UserModel model;

    public MineViewModel(Activity mActivity) {
        this.mActivity = mActivity;
        this.model = new UserModel();
        this.profileViewModel = new ProfileViewModel();
    }

    public void getUserData(final String user_id) {
        if (user_id == null || user_id.length() == 0) {
            setUser_name("请登录");
            setUser_introduction("大家都在等你哦～");
            return;
        }
        model.getData(user_id, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                UserBean userBean = JSONUtils.StringToObj(UserBean.class, obj);
                if (userBean == null) {
                    SharedPreferencesUtil.putString(mActivity, SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID, "");
                    Toast.makeText(mActivity, "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                setUser_name(userBean.getUser_name());
                setUser_introduction(userBean.getUser_introduction());
                profileViewModel.setImageUrl(userBean.getUser_head_img());
            }

            @Override
            public void failed(String err) {
                setUser_name("请登录");
                setUser_introduction("大家都在等你哦～");
                Toast.makeText(mActivity, "请登录" + err, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Bindable
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
        notifyPropertyChanged(BR.user_name);
    }

    @Bindable
    public String getUser_introduction() {
        return user_introduction;
    }

    public void setUser_introduction(String user_introduction) {
        this.user_introduction = user_introduction;
        notifyPropertyChanged(BR.user_introduction);
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
