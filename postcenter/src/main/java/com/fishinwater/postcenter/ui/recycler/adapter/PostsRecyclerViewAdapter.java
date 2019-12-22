package com.fishinwater.postcenter.ui.recycler.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.fishinwater.base.ui.adapter.BaseRecyclerViewAdapter;
import com.fishinwater.base.ui.base.BaseCustomViewModel;
import com.fishinwater.base.ui.base.BaseViewHolder;
import com.fishinwater.postcenter.ui.recycler.PostView;

/**
 * @author fishinwater-1999
 * @version 2019-12-19
 */
public class PostsRecyclerViewAdapter<T extends BaseCustomViewModel> extends BaseRecyclerViewAdapter {

    public PostsRecyclerViewAdapter(Activity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostView postView = new PostView(parent.getContext());
        return new BaseViewHolder(postView);
    }
}
