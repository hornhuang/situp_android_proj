package com.fishinwater.postmodule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postmodule.R;
import com.fishinwater.postmodule.databinding.ActivityPostBinding;
import com.fishinwater.postmodule.model.viewmodel.PostViewModel;

public class PostActivity extends BaseActivity {

    ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        binding.setViewmodel(new PostViewModel(getApplication()));

    }

    @Override
    public void action(View view) {
        Toast.makeText(this, "PostActivity", Toast.LENGTH_SHORT).show();
    }
}
