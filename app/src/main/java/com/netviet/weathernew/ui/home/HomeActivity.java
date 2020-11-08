package com.netviet.weathernew.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.netviet.weathernew.R;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.base.BaseActivity;

import java.util.List;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @Override
    protected void initView() {

    }

    @Override
    protected void dataCreate() {

    }

    @Override
    protected int layoutRes() {
        return 0;
    }
    

    @Override
    public void loadDataSuccess(List<WeatherDb> weatherDbList, Boolean addWeather) {

    }

    @Override
    public void loadDataFailed(String message) {

    }

    @Override
    public void showLoadingDB() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingAPI() {

    }
}