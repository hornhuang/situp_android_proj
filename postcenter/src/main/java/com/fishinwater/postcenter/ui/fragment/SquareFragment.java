package com.fishinwater.postcenter.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.common.RouteUtils;
import com.fishinwater.postcenter.databinding.SquareFragmentBinding;
import com.fishinwater.postcenter.model.viewmodel.PostViewModel;
import com.fishinwater.postcenter.model.viewmodel.SquareViewModel;
import com.fishinwater.postcenter.ui.recycler.adapter.PostsRecyclerViewAdapter;

@Route(path = RouteUtils.SquareFragment)
public class SquareFragment extends Fragment implements IBaseCallback<PostViewModel> {

    SquareFragmentBinding binding;

    private SquareViewModel mViewModel;

    private PostsRecyclerViewAdapter adapter;

    int page = 0;

    public static SquareFragment newInstance(String from) {
        return new SquareFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SquareFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mViewModel = new SquareViewModel(getActivity());
        binding.setSquareviewmodel(mViewModel);
        Glide.with(this)
                .load("https://img-blog.csdnimg.cn/20191218155949119.png")
                .into(binding.floatButton);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewModel = new SquareViewModel(getActivity());
        adapter = new PostsRecyclerViewAdapter<PostViewModel>(getActivity());
        binding.recycler.setAdapter(adapter);
        mViewModel.getAllPosts(page, this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new SquareViewModel(getActivity());
        // TODO: Use the ViewModel
    }


    @Override
    public void onSucceed(PostViewModel obj) {
        adapter.addData(obj);
    }

    @Override
    public void failed(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }
}
