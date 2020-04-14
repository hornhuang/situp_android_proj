package com.fishinwater.login.ui.activity;

import androidx.annotation.StringDef;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.login.R;
import com.fishinwater.login.adapter.PicturesRecyclerViewAdapter;
import com.fishinwater.login.databinding.ActivityPictureSelectorBinding;
import com.fishinwater.login.model.view.PictureViewModel;
import com.fishinwater.login.ui.view.PictureView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PictureSelectorActivity extends AppCompatActivity implements IBaseCallback<PictureViewModel> {

    ActivityPictureSelectorBinding binding;

    PictureViewModel viewModel;

    PicturesRecyclerViewAdapter<PictureViewModel> adapter;

    public static final String ICON_FLAG = "icon_flag";

    public String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picture_selector);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        flag = getIntent().getStringExtra(ICON_FLAG);

        initView();
        initRecycler();
    }

    private void initView() {
        if (flag.equals(PictureViewModel.TYPE_BACKGROUND)) {
            Glide.with(this)
                    .load("https://pic1.zhimg.com/80/v2-3563622653660f937499df24145f3f8f_hd.jpg")
                    .into(binding.headIcon);
        }else {
            Glide.with(this)
                    .load("https://pic3.zhimg.com/80/v2-eea9a36201f039fbe49695b66ed1cfe3_hd.jpg")
                    .into(binding.headIcon);
        }
        viewModel = new PictureViewModel(this);
    }

    private void initRecycler() {
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
        if (flag.equals(PictureViewModel.TYPE_BACKGROUND)) {
            adapter = new PicturesRecyclerViewAdapter<PictureViewModel>(this, PicturesRecyclerViewAdapter.TYPE_FLAG.BAC);
        }else {
            adapter = new PicturesRecyclerViewAdapter<PictureViewModel>(this, PicturesRecyclerViewAdapter.TYPE_FLAG.HEAD);
        }
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(staggeredGridLayoutManager);
        if (flag.equals(PictureViewModel.TYPE_BACKGROUND)) {
            viewModel.getPictures(PictureViewModel.TYPE_BACKGROUND, this);
        }else {
            viewModel.getPictures(PictureViewModel.TYPE_HEAD, this);
        }
    }

    @Override
    public void onSucceed(PictureViewModel bean) {
        if (bean.getIconBean().getIcon_url() != null && bean.getIconBean().getIcon_url().length() != 0) {
            adapter.addData(bean);
        }
    }

    @Override
    public void failed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @StringDef(value = {
            PictureViewModel.TYPE_HEAD,
            PictureViewModel.TYPE_BACKGROUND
    })
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Default{}

    public static void actionStart(Context context,@Default String postId) {
        Intent intent = new Intent();
        intent.setClass(context, PictureSelectorActivity.class);
        intent.putExtra(ICON_FLAG, postId);
        context.startActivity(intent);
    }
}
