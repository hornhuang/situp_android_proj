package com.fishinwater.postcenter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fishinwater.base.common.ApiUtils;
import com.fishinwater.base.common.DrawableUtils;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityPostPageBinding;
import com.fishinwater.postcenter.model.viewmodel.PostPageViewModel;

@Route(path = RouteUtils.PostPageActivity)
public class PostPageActivity extends BaseActivity {

    ActivityPostPageBinding binding;

    private PostPageViewModel viewModel;

    private static String POST_ID = "post_id";

    private static String USER_ID = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_page);
        viewModel = new PostPageViewModel(getApplication());
        binding.setPageviewmodel(viewModel);
        viewModel.setCollectImg(binding.collect);
        viewModel.setFavoriteImg(binding.favorite);

        String postId = getIntent().getStringExtra(POST_ID);
        viewModel.getData(postId);

        String userId = SharedPreferencesUtil.getString(this,
                SharedPreferencesUtil.PRE_NAME_SITUP,
                SharedPreferencesUtil.USER_ID);
        viewModel.getUser(userId);

        viewModel.setPost_id(postId);
        viewModel.setUser_id(userId);

        viewModel.setFavoriteImg(binding.favorite);
        viewModel.setCollectImg(binding.collect);

        viewModel.judgeCollect();
        viewModel.judgeFavorite();

//        ImageView imageView = findViewById(R.id.back);
//        Glide.with(this).load("https://img-blog.csdnimg.cn/20191220095844417.png").into(imageView).notifyAll();
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    @Override
    public void action(View view) {
        Toast.makeText(this, "PostPageActivity", Toast.LENGTH_SHORT).show();
    }

    public static void anctionStart(Context context, String postId, String user_id) {
        Intent intent = new Intent();
        intent.setClass(context, PostPageActivity.class);
        intent.putExtra(POST_ID, postId);
        intent.putExtra(USER_ID, user_id);
        context.startActivity(intent);
    }
}
