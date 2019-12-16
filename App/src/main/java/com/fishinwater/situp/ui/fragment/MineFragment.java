package com.fishinwater.situp.ui.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fishinwater.base.common.SharedPreferencesUtil;
import com.fishinwater.situp.databinding.MineFragmentBinding;
import com.fishinwater.situp.model.viewmodel.MineViewModel;

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
        binding.setMineViewModel(mViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new MineViewModel(getActivity());
        // TODO: Use the ViewModel
    }

    public void jumpToMyPostsAct(View view) {
        ARouter.getInstance().build("/usercenter/userposts")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .navigation();
    }

    public void jumpToMyFavoriteAct(View view) {
        ARouter.getInstance().build("/post/user_favorite")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .navigation();
    }

    public void jumpToMyCollectionAct(View view) {
        ARouter.getInstance().build("/post/user_collection")
                .withLong("key1", 666L)
                .withString("key3", "888")
                .navigation();
    }

    public void jumpToLogAct(View view) {
        if (SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.USER_KEY) == null
                || SharedPreferencesUtil.getString(getActivity(), SharedPreferencesUtil.USER_KEY).length() == 0) {
            ARouter.getInstance().build("/usercenter/login")
                    .withLong("key1", 666L)
                    .withString("key3", "888")
                    .navigation();
        }
    }

}
