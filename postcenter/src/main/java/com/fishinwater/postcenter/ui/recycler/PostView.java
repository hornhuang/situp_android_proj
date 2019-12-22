package com.fishinwater.postcenter.ui.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.fishinwater.base.ui.base.ICustomView;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.PostLayoutBinding;
import com.fishinwater.postcenter.model.viewmodel.PostViewModel;
import com.fishinwater.postcenter.ui.activity.PostPageActivity;


/**
 * @author fishinwater-1999
 * @date :2019/12/12 19:32
 */
public class PostView extends LinearLayout implements ICustomView<PostViewModel> {

    private PostLayoutBinding mBinding;

    private PostViewModel postViewModel;

    private Context mContext;

    public PostView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public PostView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public PostView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public PostView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        init();
    }


    public void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.post_layout, this, false);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postViewModel != null && postViewModel.getPostBean().getPost_content() != null) {
                    PostPageActivity.anctionStart(mContext, postViewModel.getPostBean().getPost_id(), postViewModel.getUserBean().getUser_id());
                }
            }
        });
        addView(mBinding.getRoot());
    }

    /**
     * 把 Data 设置进去
     * @param data
     */
    @Override
    public void setData(PostViewModel data) {
        mBinding.setPostviewModel(data);
        mBinding.executePendingBindings();
        this.postViewModel = data;
    }


}
