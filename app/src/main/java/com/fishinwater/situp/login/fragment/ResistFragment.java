package com.fishinwater.situp.login.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fishinwater.situp.R;
import com.fishinwater.situp.login.model.ResistViewModel;

public class ResistFragment extends Fragment {

    private ResistViewModel mViewModel;

    public static ResistFragment newInstance() {
        return new ResistFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.resist_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ResistViewModel.class);
        // TODO: Use the ViewModel
    }

}
