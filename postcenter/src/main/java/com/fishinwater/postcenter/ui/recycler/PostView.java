package com.fishinwater.postcenter.ui.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.ui.base.ICustomView;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.PostLayoutBinding;
import com.fishinwater.postcenter.model.viewmodel.PostViewModel;
import com.fishinwater.postcenter.model.viewmodel.UserPostsViewModel;
import com.fishinwater.postcenter.ui.activity.PostPageActivity;


/**
 * @author fishinwater-1999
 * @date :2019/12/12 19:32
 */
public class PostView extends LinearLayout implements ICustomView<PostBean> {

    private PostLayoutBinding mBinding;
    private PostBean viewViewModel;

    private Context mContent;

    public PostView(Context context) {
        super(context);
        this.mContent = context;
        init();
    }

    public PostView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.post_layout, this, false);
        mBinding.getRoot().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewViewModel != null && viewViewModel.getPost_content() != null) {
                    PostPageActivity.anctionStart(mContent, viewViewModel.getPost_id());
                }
            }
        });
        addView(mBinding.getRoot());
    }

    public PostView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PostView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 把 Data 设置进去
     * @param data
     */
    @Override
    public void setData(PostBean data) {
        mBinding.setPostbean(data);
        mBinding.executePendingBindings();
        this.viewViewModel = data;
    }


}
