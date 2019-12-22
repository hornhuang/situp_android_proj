package com.fishinwater.login.ui.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.base.ui.base.ICustomView;
import com.fishinwater.login.R;
import com.fishinwater.login.databinding.PictureLayoutBinding;
import com.fishinwater.login.model.view.PictureViewModel;

/**
 * @author fishinwater-1999
 * @version 2019-12-19
 */
public class PictureView extends LinearLayout implements ICustomView<PictureViewModel> {

    private PictureLayoutBinding binding;

    private PictureViewModel viewModel;

    private Activity mContext;

    private String user_id;

    private String action;

    public PictureView(Activity context, String user_id, String action) {
        super(context);
        this.mContext = context;
        this.user_id = user_id;
        this.action = action;
        init();
    }


    public void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.picture_layout, this, false);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(RouteUtils.PictureSettingActivity)
                        .withString(PictureViewModel.ACTION_TYPE, action)
                        .withString(PictureViewModel.IMG_URL, viewModel.getProfileViewModel().getImageUrl())
                        .navigation();
//                if (action.equals(PictureViewModel.TYPE_BACKGROUND)) {
//                    SharedPreferencesUtil.putString(mContext,
//                            SharedPreferencesUtil.PRE_NAME_SITUP,
//                            SharedPreferencesUtil.BACKGROUND_PIC,
//                            viewModel.getProfileViewModel().getImageUrl());
//                            Toast.makeText(mContext, "背景更新成功～", Toast.LENGTH_SHORT).show();
//                            mContext.finish();
//                }else {
//                    new UserModel().updateHeadImg(user_id,
//                            viewModel.getProfileViewModel().getImageUrl(),
//                            new IBaseCallback<String>() {
//                                @Override
//                                public void onSucceed(String obj) {
//                                    Toast.makeText(mContext, "头像更新成功～" + obj, Toast.LENGTH_SHORT).show();
//                                    mContext.finish();
//                                }
//
//                                @Override
//                                public void failed(String err) {
//
//                                }
//                            });
//                }
            }
        });
        addView(binding.getRoot());
    }

    @Override
    public void setData(PictureViewModel data) {
        binding.setPictureviewmodel(data);
        binding.executePendingBindings();
        this.viewModel = data;
    }


}
