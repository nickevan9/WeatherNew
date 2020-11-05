package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netviet.weathernew.widget.customview.CustomMoon;

public class WidgetWeatherMoon extends RelativeLayout {
    private CustomMoon moon1;
    private CustomMoon moon2;
    private CustomMoon moon3;

    private TextView tvDay1;
    private TextView tvDay2;
    private TextView tvDay3;

    public WidgetWeatherMoon(Context context) {
        super(context);
    }

    public WidgetWeatherMoon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetWeatherMoon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
