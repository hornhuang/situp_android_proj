package com.fishinwater.postcenter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fishinwater.base.common.DrawableUtils;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityPostPageBinding;
import com.fishinwater.postcenter.model.viewmodel.PostPageViewModel;

@Route(path = RouteUtils.PostPageActivity)
public class PostPageActivity extends BaseActivity {

    ActivityPostPageBinding binding;

    private PostPageViewModel viewModel;

    private static String POST_ID = "post_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_page);
        viewModel = new PostPageViewModel(getApplication());
        binding.setPageviewmodel(viewModel);

        String postId = getIntent().getStringExtra(POST_ID);
        viewModel.getData(postId);

        iniPic();
    }

    private void iniPic() {
        Glide.with(this)
                .load(DrawableUtils.UN_FAVORITE_PIC)
                .into(binding.favorite);
        Glide.with(this)
                .load(DrawableUtils.UN_COLLECT_PIC)
                .into(binding.collect);
    }

    @Override
    public void action(View view) {
        Toast.makeText(this, "PostPageActivity", Toast.LENGTH_SHORT).show();
    }

    public static void anctionStart(Context context, String postId) {
        Intent intent = new Intent();
        intent.setClass(context, PostPageActivity.class);
        intent.putExtra(POST_ID, postId);
        context.startActivity(intent);
    }
}
