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

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.postcenter.R;
import com.fishinwater.postcenter.databinding.ActivityUserPostsBinding;
import com.fishinwater.postcenter.databinding.SquareFragmentBinding;
import com.fishinwater.postcenter.model.viewmodel.SquareViewModel;
import com.fishinwater.postcenter.model.viewmodel.UserPostsViewModel;
import com.fishinwater.postcenter.ui.recycler.PostRecyclerViewAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class SquareFragment extends Fragment implements IBaseCallback<PostBean> {

    SquareFragmentBinding binding;

    private SquareViewModel mViewModel;

    private PostRecyclerViewAdapter adapter;

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

        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mViewModel = new SquareViewModel(getActivity());
        adapter = new PostRecyclerViewAdapter();
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
    public void onSucceed(PostBean obj) {
        adapter.addData(obj);
    }

    @Override
    public void failed(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }
}
