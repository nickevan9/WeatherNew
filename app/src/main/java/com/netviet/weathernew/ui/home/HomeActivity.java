package com.netviet.weathernew.ui.home;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.netviet.weathernew.R;
import com.netviet.weathernew.ui.base.BaseActivity;
import com.netviet.weathernew.ui.weather.WeatherFragment;


public class HomeActivity extends BaseActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void initView() {

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new WeatherFragment());

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_weather:
                        openFragment(new WeatherFragment());
                        return true;
                    case R.id.navigation_location:
                        openFragment(new WeatherFragment());
                        return true;
                    case R.id.navigation_theme:
                        openFragment(new WeatherFragment());
                        return true;
                }
                return false;
            };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void dataCreate() {

    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_home;
    }


}