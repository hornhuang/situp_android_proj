package com.fishinwater.postmodule.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postmodule.R;
import com.fishinwater.postmodule.databinding.ActivityPostPageBinding;
import com.fishinwater.postmodule.model.viewmodel.PostPageViewModel;

public class PostPageActivity extends BaseActivity {

    ActivityPostPageBinding binding;

    private PostPageViewModel viewModel;

    private String POST_ID = "post_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_page);
        binding.setActivity(this);
        viewModel = new PostPageViewModel(getApplication());
        binding.setPageviewmodel(viewModel);

        String postId = getIntent().getStringExtra(POST_ID);
        viewModel.getData(postId);
    }

    @Override
    public void action(View view) {
        Toast.makeText(this, "PostPageActivity", Toast.LENGTH_SHORT).show();
    }

    public void anctionStart(Activity activity, String postId) {
        Intent intent = new Intent();
        intent.setClass(activity, PostPageActivity.class);
        intent.putExtra(POST_ID, postId);
        activity.startActivity(intent);
    }
}
