package com.netviet.weathernew.ui.detail;


import com.netviet.weathernew.R;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.ui.base.BaseFragment;
import com.netviet.weathernew.widget.customwidget.WidgetDetailValue;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherDetail;

public class WeatherDetailFragment extends BaseFragment {

    private HourlyEntity hourlyEntity;
    private DailyEntity dailyEntity;
    private String timeZone;

    private WidgetWeatherDetail wgWeatherDetail;
    private WidgetDetailValue wgDetailValue;

    public static WeatherDetailFragment newInstance() {
        WeatherDetailFragment fragment = new WeatherDetailFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        wgDetailValue = requireView().findViewById(R.id.wg_detail_value);
        wgWeatherDetail = requireView().findViewById(R.id.wg_weather_detail);
    }

    @Override
    protected void dataCreate() {

    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_weather_detail;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
