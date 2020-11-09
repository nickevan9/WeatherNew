package com.netviet.weathernew.ui.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.data.model.weather.WeatherEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.adapter.HomePagerAdapter;
import com.netviet.weathernew.ui.base.BaseFragment;
import com.netviet.weathernew.ui.dialog.LoadingDialog;
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
    private HomePagerAdapter homePagerAdapter;
    private WeatherContract.Presenter weatherPresenter;

    private ImageView imgMenu;
    private TextView tvTime;
    private ImageView imgAddLocation;
    private ViewPager2 vpWeather;

    private WidgetWeatherAir wgWeatherAir;
    private WidgetWeatherHourly widgetWeatherHourly;
    private WidgetWeatherDaily widgetWeatherDaily;
    private WidgetWeatherSun widgetWeatherSun;
    private WidgetWeatherMoon widgetWeatherMoon;
    private WidgetWeatherWind widgetWeatherWind;
    private WidgetWeatherMap widgetWeatherMap;


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
        loadingDialog = new LoadingDialog(requireActivity());
        weatherPresenter = new WeatherPresenter(requireContext());
        weatherPresenter.attachView(this);
        weatherDbs = new ArrayList<>();

    }


    @Override
    protected void initView() {
        homeView = requireView().findViewById(R.id.view_home);
        scrollView = requireView().findViewById(R.id.scrollView);
        appBarLayout = requireView().findViewById(R.id.app_bar);

        imgMenu = requireView().findViewById(R.id.img_menu);
        tvTime = requireView().findViewById(R.id.tv_time);
        imgAddLocation = requireView().findViewById(R.id.img_add_location);

        vpWeather = requireView().findViewById(R.id.vp_weather);
        wgWeatherAir = requireView().findViewById(R.id.wg_weather_air);
        widgetWeatherHourly = requireView().findViewById(R.id.wg_weather_hourly);
        widgetWeatherDaily = requireView().findViewById(R.id.wg_weather_daily);
        widgetWeatherSun = requireView().findViewById(R.id.wg_weather_sun);
        widgetWeatherMoon = requireView().findViewById(R.id.wg_weather_moon);
        widgetWeatherWind = requireView().findViewById(R.id.wg_weather_wind);
        widgetWeatherMap = requireView().findViewById(R.id.wg_weather_map);


        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), getLifecycle(), weatherDbs);
        vpWeather.setAdapter(homePagerAdapter);

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


                List<HourlyEntity> hourlyEntityList = TimeUtilsExt.mapTimeToNow(weatherDb.getWeatherEntity().getListHourly(), timeZone);
                List<DailyEntity> dailyEntityList = TimeUtilsExt.mapDateToNow(weatherDb.getWeatherEntity().getListDaily(), timeZone);

                RxBus.publish(RxBus.TAG_TIME_ZONE, timeZone);
                RxBus.publish(RxBus.TAG_AIR_WEATHER, weatherDb.getAirEntity());
                RxBus.publish(RxBus.TAG_DAY_ITEM, dailyEntityList.get(0));
                RxBus.publish(RxBus.TAG_LIST_DAY_ITEM,dailyEntityList);
                RxBus.publish(RxBus.TAG_LIST_HOUR_ITEM, hourlyEntityList);
                RxBus.publish(RxBus.TAG_HOUR_ITEM,hourlyEntityList.get(0));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > oldScrollY) {
                appBarLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg_tranfer_item));
            }
            if (scrollY < oldScrollY) {
                appBarLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bg_tranfer_item));
            }

            if (scrollY == 0) {
                appBarLayout.setBackgroundColor(Color.TRANSPARENT); // required to delete elevation shadow
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        ActivityUtils.hideKeyboard(requireActivity());
    }

    @Override
    public void loadDataSuccess(List<WeatherDb> weatherDbList, Boolean addWeather) {
        weatherDbs = weatherDbList;
//        homeAdapter.applyData(weatherDbList);
        homePagerAdapter.applyData(weatherDbs);

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