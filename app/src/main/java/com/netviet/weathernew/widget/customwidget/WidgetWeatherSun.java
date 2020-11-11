package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.widget.customview.CustomSun.CustomSunView;
import com.netviet.weathernew.widget.customview.CustomSun.Time;

import java.util.List;

public class WidgetWeatherSun extends RelativeLayout {

    private ImageView imgSun;
    private CustomSunView customSunView;
    private TextView tvSunrise;
    private TextView tvSunset;

    public WidgetWeatherSun(Context context) {
        super(context);
        initView();
    }

    public WidgetWeatherSun(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetWeatherSun(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView() {
        inflate(getContext(), R.layout.widget_weather_sun, this);

        imgSun = findViewById(R.id.img_sun);
        customSunView = findViewById(R.id.custom_sun);
        tvSunrise = findViewById(R.id.tv_sun_rise);
        tvSunset = findViewById(R.id.tv_sun_set);

    }


    public void applyData(List<DailyEntity> dailyEntities) {
        String sunRise = dailyEntities.get(0).getRise();
        String sunSet = dailyEntities.get(0).getSet();

        tvSunrise.setText(sunRise);
        tvSunset.setText(sunSet);

        customSunView.setSunriseTime(new Time(Integer.parseInt(sunRise.substring(0,2)), Integer.parseInt(sunRise.substring(3,5))));
        customSunView.setSunsetTime(new Time(Integer.parseInt(sunSet.substring(0,2)), Integer.parseInt(sunSet.substring(3,5))));

        customSunView.startAnimate();
    }
}
