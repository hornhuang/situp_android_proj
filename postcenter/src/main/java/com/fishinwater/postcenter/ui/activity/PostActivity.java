package com.fishinwater.postcenter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityPostBinding;
import com.fishinwater.postcenter.model.viewmodel.PostViewModel;

public class PostActivity extends BaseActivity {

    ActivityPostBinding binding;

    PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        viewModel = new PostViewModel(this);
        binding.setViewmodel(viewModel);

    }

    @Override
    public void action(View view) {
        viewModel.post(view);
    }
}
