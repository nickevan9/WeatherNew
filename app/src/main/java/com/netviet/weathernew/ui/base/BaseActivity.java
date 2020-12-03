package com.netviet.weathernew.ui.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.netviet.weathernew.app.DataProccessor;
import com.netviet.weathernew.app.RxBus;


public abstract class BaseActivity  extends AppCompatActivity {

    public DataProccessor dataProccessor;

    protected abstract void initView();

    protected abstract void dataCreate();

    protected abstract void initData();

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        dataProccessor = new DataProccessor(this);
        initData();
        initView();
        dataCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        RxBus.unregister(this);
    }
}
