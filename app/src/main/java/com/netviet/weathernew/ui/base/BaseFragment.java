package com.netviet.weathernew.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netviet.weathernew.app.DataProccessor;

public abstract class BaseFragment extends Fragment {

    public DataProccessor dataProccessor;

    protected abstract void initView();

    protected abstract void dataCreate();

    @LayoutRes
    protected abstract int layoutRes();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataProccessor = new DataProccessor(requireContext());
        dataCreate();
        initView();
    }
}
