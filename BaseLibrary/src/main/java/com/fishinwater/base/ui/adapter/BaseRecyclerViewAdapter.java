package com.fishinwater.base.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fishinwater.base.ui.base.BaseCustomViewModel;
import com.fishinwater.base.ui.base.BaseViewHolder;
import com.fishinwater.base.ui.base.ICustomView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fishinwater-1999
 * @date :2019/12/12 19:38
 */
public class BaseRecyclerViewAdapter<T extends BaseCustomViewModel> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> mItems;

    private Activity activity;

    public BaseRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
        setData(new ArrayList<T>());
    }

    private void setData(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void addData(T obj) {
        Log.d("123123", mItems.size() + "");
        mItems.add(obj);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(null);
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
