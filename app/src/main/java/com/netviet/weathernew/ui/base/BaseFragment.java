package com.netviet.weathernew.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.app.DataProccessor;
import com.example.weatherapp.app.RxBus;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends Fragment
{

    public DataProccessor dataProccessor;
    @LayoutRes
    protected abstract int layoutRes();

    protected abstract void dataCreate();

    protected abstract void initView();

    protected abstract void fragmentBackPressed();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataProccessor = new DataProccessor(requireContext());
        dataCreate();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();



        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                fragmentBackPressed();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.unregister(this);
    }
}
