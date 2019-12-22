package com.fishinwater.login.model.view;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.data.protocol.IconBean;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.model.PictureModel;
import com.fishinwater.base.ui.adapter.ProfileViewModel;
import com.fishinwater.base.ui.base.BaseCustomViewModel;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

/**
 * @author fishinwater-1999
 * @version 2019-12-19
 */
public class PictureViewModel extends BaseCustomViewModel {

    @StringDef(value = {
            TYPE_HEAD,
            TYPE_BACKGROUND
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Default{}

    /**
     * 用于标记跳转参数
     */
    public static final String ACTION_TYPE = "picture_action";

    /**
     * 跳转 / 上传时
     * 所要携带的参数
     */
    public static final String TYPE_HEAD = "head";
    public static final String TYPE_BACKGROUND = "bac";

    /**
     * 图片 PictureSettingActivity 中，图片 url 参数传递使用
     */
    public static final String IMG_URL = "setting_img_url";

    ProfileViewModel profileViewModel;

    IconBean iconBean;

    public PictureViewModel(Activity activity) {
        super(activity);
        this.profileViewModel = new ProfileViewModel();
    }

    public void getPictures(@Default String getType, final IBaseCallback<PictureViewModel> callback) {
        new PictureModel().getPicturesByFlag(getType, new IBaseCallback<String>() {
            @Override
            public void onSucceed(String obj) {
                Consumer<IconBean> consumer = new Consumer<IconBean>() {
                    @Override
                    public void accept(IconBean iconBean) throws Exception {
                        if (iconBean.getIcon_url() == null || iconBean.getIcon_url().length() == 0) {
                            return ;
                        }
                        PictureViewModel viewModel = new PictureViewModel(mContext);
                        viewModel.setIconBean(iconBean);
                        callback.onSucceed(viewModel);
                    }
                };
                List<IconBean> list = JSONUtils.jsonStrtoList(IconBean.class, obj);
                Observable.fromIterable(list)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer);
            }

            @Override
            public void failed(String err) {

            }
        });
    }

    @Bindable
    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    public void setProfileViewModel(ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;
        notifyPropertyChanged(BR.profileViewModel);
    }

@   Bindable
    public IconBean getIconBean() {
        return iconBean;
    }

    public void setIconBean(IconBean iconBean) {
        this.iconBean = iconBean;
        profileViewModel.setImageUrl(iconBean.getIcon_url());
        notifyPropertyChanged(BR.iconBean);
    }
}
