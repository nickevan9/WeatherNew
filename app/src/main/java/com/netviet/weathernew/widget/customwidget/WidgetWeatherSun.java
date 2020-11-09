package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.widget.customview.CustomSun.CustomSunView;
import com.netviet.weathernew.widget.customview.CustomSun.Time;

import java.util.List;

public class WidgetWeatherSun extends RelativeLayout {

    private ImageView imgSun;
    private CustomSunView customSunView;

    public WidgetWeatherSun(Context context) {
        super(context);
    }

    public WidgetWeatherSun(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetWeatherSun(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView() {
        inflate(getContext(), R.layout.widget_weather_sun, this);

        imgSun = findViewById(R.id.img_sun);
        customSunView = findViewById(R.id.custom_sun);

    }


    private void applyData(List<DailyEntity> dailyEntities) {
        String sunRise = dailyEntities.get(0).getRise();
        String sunSet = dailyEntities.get(0).getSet();

        customSunView.setSunriseTime(new Time(Integer.parseInt(sunRise.substring(0,2)), Integer.parseInt(sunRise.substring(2,4))));
        customSunView.setSunsetTime(new  Time(Integer.parseInt(sunSet.substring(0,2)), Integer.parseInt(sunSet.substring(2,4))));

        customSunView.startAnimate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        RxBus.subscribe(RxBus.TAG_LIST_DAY_ITEM, this, listDayObject -> {
            List<DailyEntity> dailyEntities = (List<DailyEntity>) listDayObject;
            applyData(dailyEntities);
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RxBus.unregister(this);
    }
}
