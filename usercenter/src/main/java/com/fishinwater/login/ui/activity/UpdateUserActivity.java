package com.fishinwater.login.ui.activity;

import androidx.annotation.IntDef;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.JSONUtils;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.data.protocol.UserBean;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.base.ui.base.BaseActivity;
import com.fishinwater.login.R;
import com.fishinwater.login.databinding.ActivityUpdateUserBinding;
import com.fishinwater.login.model.view.PictureViewModel;
import com.fishinwater.login.model.view.UpdateViewModel;

/**
 * @author fishinwater-1999
 */
@Route(path = RouteUtils.UpdateUserActivity)
public class UpdateUserActivity extends BaseActivity {

    private ActivityUpdateUserBinding binding;

    private String user_id;

    private UpdateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_user);
        viewModel = new UpdateViewModel(this);
        user_id = getIntent().getStringExtra("user_id");
        viewModel.init(user_id);
        binding.setUpdateViewModel(viewModel);

        init();
        iniClick();

    }

    private void iniClick() {
        binding.userName.setInputType(EditorInfo.TYPE_NULL);
        binding.userIntroduce.setInputType(EditorInfo.TYPE_NULL);
        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.userIntroduce.getInputType() == EditorInfo.TYPE_NULL) {
                    binding.userName.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    binding.userIntroduce.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    loadCircleImg("https://img-blog.csdnimg.cn/20191219175506302.png", binding.edit);
                }else {
                    binding.userName.setInputType(EditorInfo.TYPE_NULL);
                    binding.userIntroduce.setInputType(EditorInfo.TYPE_NULL);
                    loadCircleImg("https://img-blog.csdnimg.cn/20191219175510995.png", binding.edit);
                    new UserModel().updateIntroduction(SharedPreferencesUtil.getString(UpdateUserActivity.this,
                                    SharedPreferencesUtil.PRE_NAME_SITUP,
                                    SharedPreferencesUtil.USER_ID),
                            binding.userIntroduce.getText().toString(),
                            new IBaseCallback<String>() {
                        @Override
                        public void onSucceed(String obj) {
                            viewModel.init(user_id);
                            init();
                        }

                        @Override
                        public void failed(String err) {

                        }
                    });
                }

            }
        });

        binding.userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelectorActivity.actionStart(UpdateUserActivity.this, PictureViewModel.TYPE_HEAD);
            }
        });

        binding.bacImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelectorActivity.actionStart(UpdateUserActivity.this, PictureViewModel.TYPE_BACKGROUND);
            }
        });
    }

    private void init() {
        loadCircleImg("https://img-blog.csdnimg.cn/20191219175510995.png", binding.edit);
        loadCircleImg("https://img-blog.csdnimg.cn/20191217224237392.png",binding.userImg);
        loadRectImg(SharedPreferencesUtil.getString(this, SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.BACKGROUND_PIC), binding.bacImg);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.init(user_id);
        init();
    }
}
