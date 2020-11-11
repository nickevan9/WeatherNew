package com.netviet.weathernew.ui.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.netviet.weathernew.R;
import com.netviet.weathernew.app.ActivityUtils;
import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.adapter.WeatherAdapter;
import com.netviet.weathernew.ui.base.BaseFragment;
import com.netviet.weathernew.ui.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;


public class WeatherFragment extends BaseFragment implements WeatherContract.View {

    private List<WeatherDb> weatherDbs;
    private LoadingDialog loadingDialog;
//    private HomePagerAdapter homePagerAdapter;
    private WeatherAdapter weatherAdapter;
    private WeatherContract.Presenter weatherPresenter;

    private ImageView imgMenu;
    private TextView tvTime;
    private ImageView imgAddLocation;
    private ViewPager2 vpWeather;



    private CoordinatorLayout homeView;
    private AppBarLayout appBarLayout;
    private NestedScrollView scrollView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void dataCreate() {

        homeView = requireView().findViewById(R.id.view_home);
//        scrollView = requireView().findViewById(R.id.scrollView);
        appBarLayout = requireView().findViewById(R.id.app_bar);

        imgMenu = requireView().findViewById(R.id.img_menu);
        tvTime = requireView().findViewById(R.id.tv_time);
        imgAddLocation = requireView().findViewById(R.id.img_add_location);

        vpWeather = requireView().findViewById(R.id.vp_weather);


        loadingDialog = new LoadingDialog(requireActivity());
        weatherDbs = new ArrayList<>();
        weatherPresenter = new WeatherPresenter(requireContext());
//        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), getLifecycle(), weatherDbs);
        weatherAdapter = new WeatherAdapter(requireContext(),weatherDbs);

        vpWeather.setAdapter(weatherAdapter);
        weatherPresenter.attachView(this);

        vpWeather.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                WeatherDb weatherDb = weatherDbs.get(position);

                String timeZone = weatherDb.getWeatherEntity().getLoc().getTzname();

                tvTime.setText(TimeUtilsExt.formatTimeNowDay(timeZone));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        imgAddLocation.setOnClickListener(view -> {

        });


    }


    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ActivityUtils.hideKeyboard(requireActivity());
    }

    @Override
    public void loadDataSuccess(List<WeatherDb> weatherDbList, Boolean addWeather) {
        weatherDbs = weatherDbList;
        weatherAdapter.applyData(weatherDbs);

        if (addWeather) {
            vpWeather.setCurrentItem(weatherDbs.size(), false);
        }

        new Handler().postDelayed(() -> loadingDialog.dismissDialog(),1000);

    }

    @Override
    public void loadDataFailed(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
        loadingDialog.dismissDialog();
    }

    @Override
    public void showLoadingDB() {
        if (loadingDialog.getmDialog().isShowing()) {
            loadingDialog.dismissDialog();
            loadingDialog.startLoading(1);
        } else {
            loadingDialog.startLoading(1);
        }
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismissDialog();
    }

    @Override
    public void showLoadingAPI() {
        if (loadingDialog.getmDialog().isShowing()) {
            loadingDialog.dismissDialog();
            loadingDialog.startLoading(0);
        } else {
            loadingDialog.startLoading(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        weatherPresenter.detachView(this);
        weatherPresenter.destroy();
    }
}