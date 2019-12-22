package com.fishinwater.postcenter.ui.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityPublishPostBinding;
import com.fishinwater.postcenter.model.viewmodel.PostViewModel;

@Route(path = RouteUtils.PublishPostActivity)
public class PublishPostActivity extends BaseActivity {

    ActivityPublishPostBinding binding;

    PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_publish_post);
        viewModel = new PostViewModel(this);
        binding.setViewmodel(viewModel);
        binding.setPostbean(viewModel.getPostBean());

        Glide.with(this)
                .load("https://img-blog.csdnimg.cn/20191220095844417.png")
                .into(binding.back);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void action(View view) {
        viewModel.post(view);
    }
}
