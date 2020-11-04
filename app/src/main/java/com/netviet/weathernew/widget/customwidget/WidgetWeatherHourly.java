package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.netviet.weathernew.R;

public class WidgetWeatherHourly extends RelativeLayout {
    public WidgetWeatherHourly(Context context) {
        super(context);
    }

    public WidgetWeatherHourly(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetWeatherHourly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView(){
        inflate(getContext(), R.layout.widget_weather_hourly,this);
    }
}
