package com.fishinwater.login.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.base.ui.adapter.BaseRecyclerViewAdapter;
import com.fishinwater.base.ui.base.BaseCustomViewModel;
import com.fishinwater.base.ui.base.BaseViewHolder;
import com.fishinwater.login.ui.view.PictureView;

/**
 * @author fishinwater-1999
 * @version 2019-12-19
 */
public class PicturesRecyclerViewAdapter<T extends BaseCustomViewModel> extends BaseRecyclerViewAdapter {

    /**
     * 判断要获得哪种数据
     */
    public enum TYPE_FLAG {
        /**
         * 用于背景
         */
        BAC("bac"),
        /**
         * 用于头像
         */
        HEAD("head");

        private String action;

        TYPE_FLAG(String action){
            this.action = action;
        }

        public String getAction() {
            return action;
        }
    }

    TYPE_FLAG flag ;

    public PicturesRecyclerViewAdapter(Activity activity, TYPE_FLAG flag) {
        super(activity);
        this.flag = flag;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PictureView pictureView = new PictureView(getActivity(),
                SharedPreferencesUtil.getString(getActivity(),
                        SharedPreferencesUtil.PRE_NAME_SITUP,
                        SharedPreferencesUtil.USER_ID),
                flag.getAction());
        return new BaseViewHolder(pictureView);
    }
}
