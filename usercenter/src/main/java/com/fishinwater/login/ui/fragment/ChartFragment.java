package com.fishinwater.login.ui.fragment;

import androidx.annotation.StringDef;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.login.R;
import com.fishinwater.login.adapter.PicturesRecyclerViewAdapter;
import com.fishinwater.login.databinding.ActivityPictureSelectorBinding;
import com.fishinwater.login.model.view.ChartViewModel;
import com.fishinwater.login.model.view.PictureViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ChartFragment extends Fragment implements IBaseCallback<PictureViewModel> {

    ActivityPictureSelectorBinding binding;

    PictureViewModel viewModel;

    PicturesRecyclerViewAdapter<PictureViewModel> adapter;

    private ChartViewModel mViewModel;

    public static ChartFragment newInstance(String from) {
        return new ChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_picture_selector, container, false);

        initView();
        initRecycler();

        return binding.getRoot();
    }

    private void initView() {
        Glide.with(this)
                .load("https://pic1.zhimg.com/80/v2-3563622653660f937499df24145f3f8f_hd.jpg")
                .into(binding.headIcon);
        viewModel = new PictureViewModel(getActivity());
    }

    private void initRecycler() {
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
        adapter = new PicturesRecyclerViewAdapter<PictureViewModel>(getActivity(), PicturesRecyclerViewAdapter.TYPE_FLAG.BAC);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(staggeredGridLayoutManager);
        viewModel.getPictures(PictureViewModel.TYPE_BACKGROUND, this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChartViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onSucceed(PictureViewModel bean) {
        if (bean.getIconBean().getIcon_url() != null && bean.getIconBean().getIcon_url().length() != 0) {
            adapter.addData(bean);
        }
    }

    @Override
    public void failed(String err) {
        Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
    }

    @StringDef(value = {
            PictureViewModel.TYPE_HEAD,
            PictureViewModel.TYPE_BACKGROUND
    })
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Default{}
}
