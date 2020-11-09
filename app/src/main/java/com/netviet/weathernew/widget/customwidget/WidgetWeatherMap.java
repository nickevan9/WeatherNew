package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.netviet.weathernew.R;

public class WidgetWeatherMap extends RelativeLayout {
    public WidgetWeatherMap(Context context) {
        super(context);
    }

    public WidgetWeatherMap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetWeatherMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView(){
        inflate(getContext(), R.layout.widget_weather_map,this);
    }
}
