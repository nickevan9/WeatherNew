package com.netviet.weathernew.ui.weather;

import android.content.Intent;
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
import com.mapbox.geojson.Point;
import com.netviet.weathernew.R;
import com.netviet.weathernew.app.ActivityUtils;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.adapter.WeatherAdapter;
import com.netviet.weathernew.ui.base.BaseFragment;
import com.netviet.weathernew.ui.dialog.LoadingDialog;
import com.netviet.weathernew.ui.place.PlaceActivity;
import com.netviet.weathernew.widget.customwidget.WidgetAdView;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherAir;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherDaily;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherHourly;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherMap;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherMoon;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherSun;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherWind;

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

    private WidgetWeatherAir widgetWeatherAir;
    private WidgetWeatherHourly widgetWeatherHourly;
    private WidgetWeatherDaily widgetWeatherDaily;
    private WidgetAdView widgetAdView;
    private WidgetWeatherSun widgetWeatherSun;
    private WidgetWeatherMoon widgetWeatherMoon;
    private WidgetWeatherWind widgetWeatherWind;
    private WidgetWeatherMap widgetWeatherMap;



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
        scrollView = requireView().findViewById(R.id.scrollView);
        appBarLayout = requireView().findViewById(R.id.app_bar);

        widgetWeatherAir= requireView().findViewById(R.id.wg_weather_air);
        widgetWeatherHourly= requireView().findViewById(R.id.wg_weather_hourly);
        widgetWeatherDaily= requireView().findViewById(R.id.wg_weather_daily);
        widgetAdView= requireView().findViewById(R.id.wg_ad_view);
        widgetWeatherSun = requireView().findViewById(R.id.wg_weather_sun);
        widgetWeatherMoon= requireView().findViewById(R.id.wg_weather_moon);
        widgetWeatherWind= requireView().findViewById(R.id.wg_weather_wind);
        widgetWeatherMap= requireView().findViewById(R.id.wg_weather_map);

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

                List<HourlyEntity> hourlyEntityList = TimeUtilsExt.mapTimeToNow(weatherDb.getWeatherEntity().getListHourly(), timeZone);
                List<DailyEntity> dailyEntityList = TimeUtilsExt.mapDateToNow(weatherDb.getWeatherEntity().getListDaily(), timeZone);

                widgetWeatherAir.applyData(weatherDb.getAirEntity());
                widgetWeatherHourly.applyData(hourlyEntityList,timeZone);
                widgetWeatherDaily.applyData(dailyEntityList,timeZone);
                widgetWeatherSun.applyData(dailyEntityList,timeZone);
                widgetWeatherMoon.applyData(dailyEntityList,timeZone);
                widgetWeatherWind.applyData(hourlyEntityList.get(0));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        imgAddLocation.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), PlaceActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });


    }


    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ActivityUtils.hideKeyboard(requireActivity());

        RxBus.subscribe(RxBus.TAG_LOCATION_ADD,this,pointObject -> {
            Point point = (Point) pointObject;
            Double lat = point.latitude();
            Double lon = point.longitude();
            weatherPresenter.getSingleWeather(lat, lon);
            RxBus.unregister(this);
        });
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