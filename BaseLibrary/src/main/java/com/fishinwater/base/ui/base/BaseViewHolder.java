package com.fishinwater.base.ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author fishinwater-1999
 * @date :2019/12/12 19:25
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    ICustomView view;

    public BaseViewHolder(@NonNull ICustomView itemView) {
        super((View) itemView);
        this.view = itemView;
    }

    public void bind(@NonNull BaseCustomViewModel item) {
        view.setData(item);
    }

}

