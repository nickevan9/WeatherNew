package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;

public class WidgetWeatherDetail extends RelativeLayout {
    private TextView tvLocation;
    private TextView tvWeatherStatus;
    private TextView tvTemp;
    private TextView tvTempMax;
    private TextView tvTempMin;

    public WidgetWeatherDetail(Context context) {
        super(context);
        initView();
    }

    public WidgetWeatherDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetWeatherDetail(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView(){
        inflate(getContext(), R.layout.widget_weather_detail,this);

        tvLocation = findViewById(R.id.tv_location);
        tvWeatherStatus = findViewById(R.id.tv_weather_status);
        tvTemp = findViewById(R.id.tv_temp);
        tvTempMax = findViewById(R.id.tv_temp_max);
        tvTempMin = findViewById(R.id.tv_temp_min);
    }

    public void applyDataHourly(HourlyEntity hourlyEntity){

        tvTemp.setText(getContext().getString(R.string.set_temp,String.valueOf((int) Double.parseDouble(hourlyEntity.getTempFeel()))));
        tvWeatherStatus.setText(hourlyEntity.getTxt());
    }

    public void applyDataDaily( DailyEntity dailyEntity){
        tvTempMax.setText(getContext().getString(R.string.set_temp,String.valueOf(dailyEntity.getTempMax())));
        tvTempMin.setText(getContext().getString(R.string.set_temp,String.valueOf(dailyEntity.getTempMin())));

    }

    public void applyName(String name){
        tvLocation.setText(name);
    }
}
