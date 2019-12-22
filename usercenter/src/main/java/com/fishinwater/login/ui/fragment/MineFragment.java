package com.fishinwater.login.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.base.common.preferences.SharedPreferencesUtil;
import com.fishinwater.login.databinding.MineFragmentBinding;
import com.fishinwater.login.model.MineViewModel;

@Route(path = RouteUtils.MineFragment)
public class MineFragment extends Fragment {

    MineFragmentBinding binding;

    private MineViewModel mViewModel;

    public static MineFragment newInstance(String from) {
        return new MineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MineFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.setMyFragment(this);
        mViewModel = new MineViewModel(getActivity());
        binding.setMineViewModel(mViewModel);
        mViewModel.getUserData(SharedPreferencesUtil.getString(getActivity(),
                SharedPreferencesUtil.PRE_NAME_SITUP,
                SharedPreferencesUtil.USER_ID));

        initView();
        return view;
    }

    private void initView() {
        loadRectImg("https://img-blog.csdnimg.cn/20191219193521178.png", binding.postsImg);
        loadRectImg("https://img-blog.csdnimg.cn/20191219193510511.png", binding.favoritesImg);
        loadRectImg("https://img-blog.csdnimg.cn/20191219193507594.png", binding.collectsImg);
        loadRectImg("https://img-blog.csdnimg.cn/20191219193455856.png", binding.logoutImg);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new MineViewModel(getActivity());
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getUserData(SharedPreferencesUtil.getString(getActivity(),
                SharedPreferencesUtil.PRE_NAME_SITUP,
                SharedPreferencesUtil.USER_ID));
    }

    public void jumpToUpdateAct(View view) {
        if (SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID) == null
                || SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID).length() == 0) {
            jumpToLogAct(view);
        } else {
            ARouter.getInstance().build(RouteUtils.UpdateUserActivity)
                    .withString("user_id", SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID))
                    .navigation();
        }
    }

    public void jumpToMyPostsAct(View view) {
        checkUserStatus(view);
        ARouter.getInstance().build(RouteUtils.UserPostsActivity)
                .withString("user_id", SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID))
                .navigation();
    }

    public void jumpToMyFavoriteAct(View view) {
        checkUserStatus(view);
        ARouter.getInstance().build(RouteUtils.UserFavoritePostsActivity)
                .withString("user_id", SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID))
                .navigation();
    }

    public void jumpToMyCollectionAct(View view) {
        checkUserStatus(view);
        ARouter.getInstance().build(RouteUtils.UserCollectPostsActivity)
                .withString("user_id", SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID))
                .navigation();
    }

    private void checkUserStatus(View view) {
        if (SharedPreferencesUtil.isLogedin(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP)) {
            return;
        }
        jumpToLogAct(view);
    }

    public void jumpToLogAct(View view) {
        SharedPreferencesUtil.putString(getActivity(), SharedPreferencesUtil.PRE_NAME_SITUP, SharedPreferencesUtil.USER_ID, null);
        ARouter.getInstance().build(RouteUtils.LogActivity)
                .navigation();
    }

    private void loadRectImg(String url, ImageView view) {
        Glide.with(this)
                .load(url)
                .into(view);
    }
}
