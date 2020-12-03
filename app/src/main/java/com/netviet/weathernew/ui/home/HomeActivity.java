package com.netviet.weathernew.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.AppBarLayout;
import com.mapbox.geojson.Point;
import com.netviet.weathernew.R;
import com.netviet.weathernew.app.ActivityUtils;
import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.adapter.HomePagerAdapter;
import com.netviet.weathernew.ui.base.BaseActivity;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    public static final int REQUEST_CODE_AUTOCOMPLETE = 100;

    private HomeContract.Presenter homePresenter;
    private ViewPager2 vpHome;
    private ImageView imgDropDown;
    private TextView tvLocation;
    private ImageView imgShare;
    private ImageView imgSetting;
    private ImageView imgAddLocation;
    private List<WeatherDb> weatherDbs;
    private LoadingDialog loadingDialog;
    private HomePagerAdapter homePagerAdapter;
    private CoordinatorLayout homeView;
    private AppBarLayout appBarLayout;
    private NestedScrollView scrollView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WidgetWeatherAir wgWeatherAir;
    private WidgetWeatherHourly wgWeatherHourly;
    private WidgetWeatherDaily wgWeatherDaily;
    private WidgetAdView wgAdView;
    private WidgetWeatherSun wgWeatherSun;
    private WidgetWeatherMoon wgWeatherMoon;
    private WidgetWeatherWind wgWeatherWind;
    private WidgetWeatherMap wgWeatherMap;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Serializable dataPlace = intent.getSerializableExtra("dataPlace");
                        Double lat = ((Point) dataPlace).latitude();
                        Double lon = ((Point) dataPlace).longitude();
                        homePresenter.getSingleWeather(lat, lon);
                        // Handle the Intent
                    }
                }
            });


    @Override
    protected void initData() {
        loadingDialog = new LoadingDialog(this);
        homePresenter = new HomePresenter(this);
        weatherDbs = new ArrayList<>();
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), getLifecycle(), weatherDbs);
    }


    @Override
    protected void initView() {

        homeView = findViewById(R.id.view_home);
        imgDropDown = findViewById(R.id.img_drop_down);
        imgAddLocation = findViewById(R.id.img_add_location);
        imgSetting = findViewById(R.id.img_setting);
        imgShare = findViewById(R.id.img_share);
        tvLocation = findViewById(R.id.tv_name_city);
        appBarLayout = findViewById(R.id.app_bar);
        scrollView = findViewById(R.id.scrollView);
        vpHome = findViewById(R.id.vPHome);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        wgWeatherAir = findViewById(R.id.wg_weather_air);
        wgWeatherHourly = findViewById(R.id.wg_weather_hourly);
        wgWeatherDaily = findViewById(R.id.wg_weather_daily);
        wgAdView = findViewById(R.id.wg_ad_view);
        wgWeatherSun = findViewById(R.id.wg_weather_sun);
        wgWeatherMoon = findViewById(R.id.wg_weather_moon);
        wgWeatherWind = findViewById(R.id.wg_weather_wind);
        wgWeatherMap = findViewById(R.id.wg_weather_map);

        vpHome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                WeatherDb weatherDb = weatherDbs.get(position);

                String timeZone = weatherDb.getWeatherEntity().getLoc().getTzname();

                List<HourlyEntity> hourlyEntityList = TimeUtilsExt.mapTimeToNow(weatherDb.getWeatherEntity().getListHourly(), timeZone);
                List<DailyEntity> dailyEntityList = TimeUtilsExt.mapDateToNow(weatherDb.getWeatherEntity().getListDaily(), timeZone);

                wgWeatherAir.applyData(weatherDb.getAirEntity());
                wgWeatherHourly.applyData(hourlyEntityList,timeZone);
                wgWeatherDaily.applyData(dailyEntityList,timeZone);
                wgWeatherSun.applyData(dailyEntityList,timeZone);
                wgWeatherMoon.applyData(dailyEntityList,timeZone);
                wgWeatherWind.applyData(hourlyEntityList.get(0));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        imgAddLocation.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, PlaceActivity.class);
            mStartForResult.launch(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > oldScrollY) {
                appBarLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.bg_tranfer_item));
            }
            if (scrollY < oldScrollY) {
                appBarLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.bg_tranfer_item));
            }

            if (scrollY == 0) {
                appBarLayout.setBackgroundColor(Color.TRANSPARENT); // required to delete elevation shadow
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {

            homePresenter.fetchAllWeather(weatherDbs);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Serializable result = data.getSerializableExtra("dataPlace");
                assert result != null;
                Double lat = ((Point) result).latitude();
                Double lon = ((Point) result).longitude();
                homePresenter.getSingleWeather(lat, lon);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ActivityUtils.hideKeyboard(this);

    }

    @Override
    protected void dataCreate() {
        homePresenter.attachView(this);
    }


    @Override
    protected int layoutRes() {
        return R.layout.activity_home;
    }


    @Override
    public void loadDataSuccess(List<WeatherDb> weatherDbList, Boolean addWeather) {
        weatherDbs = weatherDbList;
//        homeAdapter.applyData(weatherDbList);
        homePagerAdapter.applyData(weatherDbs);

        if (addWeather) {
            vpHome.setCurrentItem(weatherDbs.size(), false);
        }
        new Handler().postDelayed(() -> loadingDialog.dismissDialog(),1000);
    }

    @Override
    public void loadDataFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        loadingDialog.dismissDialog();
    }

    @Override
    public void refreshDataSuccess(List<WeatherDb> weatherDbList) {
        if (swipeRefreshLayout.isRefreshing()){
            weatherDbs = weatherDbList;
            homePagerAdapter.applyData(weatherDbs);
            swipeRefreshLayout.setRefreshing(false);
        }
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
}