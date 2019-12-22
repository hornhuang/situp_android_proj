package com.fishinwater.login.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.model.UserModel;
import com.fishinwater.login.R;
import com.fishinwater.login.databinding.ActivityPictureSelectorBinding;
import com.fishinwater.login.databinding.ActivityPictureSettingBinding;
import com.fishinwater.login.databinding.PictureLayoutBinding;
import com.fishinwater.login.model.view.PictureViewModel;

/**
 * @author fishinwate-1999
 */
@Route(path = "/user/set_picture")
public class PictureSettingActivity extends AppCompatActivity {

    private ActivityPictureSettingBinding binding;

    private String action = "";

    private String user_id = "";

    private String imgUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_picture_setting);

        Bundle bundle = getIntent().getExtras();
        action = bundle.getString(PictureViewModel.ACTION_TYPE);
        user_id = SharedPreferencesUtil.getString(this, SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID);
        imgUrl = bundle.getString(PictureViewModel.IMG_URL);

        iniView();
        iniClick();
    }

    private void iniClick() {
        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (action.equals(PictureViewModel.TYPE_BACKGROUND)) {
                    SharedPreferencesUtil.putString(PictureSettingActivity.this,
                            SharedPreferencesUtil.PRE_NAME_SITUP,
                            SharedPreferencesUtil.BACKGROUND_PIC,
                            imgUrl);
                    Toast.makeText(PictureSettingActivity.this, "背景更新成功～", Toast.LENGTH_SHORT).show();
                }else {
                    new UserModel().updateHeadImg(user_id,
                            imgUrl,
                            new IBaseCallback<String>() {
                                @Override
                                public void onSucceed(String obj) {
                                    Toast.makeText(PictureSettingActivity.this, "头像更新成功～" + obj, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void failed(String err) {

                                }
                            });
                }
                finish();
            }
        });
    }

    private void iniView() {
        if (action == null) {
            return;
        }
        if (action.equals(PictureViewModel.TYPE_BACKGROUND)) {
            binding.upload.setText("设为背景");
        }else {
            binding.upload.setText("设为头像");
        }
        Glide.with(this).load(imgUrl).placeholder(R.drawable.place_houlder).into(binding.image);
    }


}
