package com.fishinwater.postcenter.ui.recycler;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fishinwater.base.ui.base.BaseCustomViewModel;
import com.fishinwater.base.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fishinwater-1999
 * @date :2019/12/12 19:38
 */
public class PostRecyclerViewAdapter<T extends BaseCustomViewModel> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> mItems;

    public PostRecyclerViewAdapter() {
        setData(new ArrayList<T>());
    }

    private void setData(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void addData(T obj) {
        mItems.add(obj);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostView pictureTitleView = new PostView(parent.getContext());
        return new BaseViewHolder(pictureTitleView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItems != null && mItems.size() > 0) {
            return mItems.size();
        }
        return 0;
    }
}
