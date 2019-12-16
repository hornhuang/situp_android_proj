package com.fishinwater.postcenter.ui.activity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fishinwater.base.callback.MyObjCallback;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.rx.BaseActivity;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityUserPostsBinding;
import com.fishinwater.postcenter.model.viewmodel.UserPostsViewModel;
import com.fishinwater.postcenter.ui.recycler.PostRecyclerViewAdapter;

@Route(path = "/post/user_posts")
public class UserPostsActivity extends BaseActivity implements MyObjCallback<PostBean> {

    ActivityUserPostsBinding binding;

    UserPostsViewModel viewModel;

    PostRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_posts);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new UserPostsViewModel(this);
        adapter = new PostRecyclerViewAdapter();
        binding.recycler.setAdapter(adapter);
        viewModel.getUserPosts("445417a0-560c-40c9-aeeb-bab98f501be1", this);
    }

    @Override
    public void action(View view) {

    }

    @Override
    public void onSucceed(PostBean bean) {
//        Log.d("qweqwe", list + "");
//        if (list != null)
//            Toast.makeText(this, list.size(), Toast.LENGTH_SHORT).show();
         adapter.addData(bean);
    }

    @Override
    public void onFailed(String errMsg) {

    }
}
