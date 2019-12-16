package com.fishinwater.situp.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fishinwater.situp.R;
import com.fishinwater.situp.model.viewmodel.SquareViewModel;

public class SquareFragment extends Fragment {

    private SquareViewModel mViewModel;

    public static SquareFragment newInstance(String from) {
        return new SquareFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.square_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SquareViewModel.class);
        // TODO: Use the ViewModel
    }

}
