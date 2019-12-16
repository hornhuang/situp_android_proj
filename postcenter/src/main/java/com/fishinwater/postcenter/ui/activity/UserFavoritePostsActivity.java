package com.fishinwater.postcenter.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.base.callback.MyObjCallback;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityUserPostsBinding;
import com.fishinwater.postcenter.model.viewmodel.UserPostsViewModel;
import com.fishinwater.postcenter.ui.recycler.PostRecyclerViewAdapter;

@Route(path = RouteUtils.UserFavoritePostsActivity)
public class UserFavoritePostsActivity extends BaseActivity implements MyObjCallback<PostBean> {

    ActivityUserPostsBinding binding;

    UserPostsViewModel viewModel;

    PostRecyclerViewAdapter adapter;

    private static String POST_ID = "post_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_posts);
        String postId = getIntent().getStringExtra(POST_ID);

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new UserPostsViewModel(this);
        adapter = new PostRecyclerViewAdapter();
        binding.recycler.setAdapter(adapter);
        viewModel.getUserFavoritePosts(postId, this);
    }

    @Override
    public void action(View view) {

    }

    @Override
    public void onSucceed(PostBean bean) {
        adapter.addData(bean);
    }

    @Override
    public void onFailed(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
    }

    public static void actionStart(Context context, String postId) {
        Intent intent = new Intent();
        intent.setClass(context, UserFavoritePostsActivity.class);
        intent.putExtra(POST_ID, postId);
        context.startActivity(intent);
    }
}
