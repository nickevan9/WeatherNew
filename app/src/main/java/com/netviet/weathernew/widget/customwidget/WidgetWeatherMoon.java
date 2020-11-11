package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.widget.customview.CustomMoon;

import java.util.List;

public class WidgetWeatherMoon extends RelativeLayout {
    private CustomMoon moon1;
    private CustomMoon moon2;
    private CustomMoon moon3;

    private TextView tvDay1;
    private TextView tvDay2;
    private TextView tvDay3;

    private TextView tvMoonStatus1;
    private TextView tvMoonStatus2;
    private TextView tvMoonStatus3;

    private String timeZone;

    public WidgetWeatherMoon(Context context) {
        super(context);
        initView();
    }

    public WidgetWeatherMoon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetWeatherMoon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView() {
        inflate(getContext(), R.layout.widget_weather_moon, this);

        timeZone = "";

        moon1 = findViewById(R.id.moon_1);
        moon2 = findViewById(R.id.moon_2);
        moon3 = findViewById(R.id.moon_3);

        tvDay1 = findViewById(R.id.tv_day_1);
        tvDay2 = findViewById(R.id.tv_day_2);
        tvDay3 = findViewById(R.id.tv_day_3);

        tvMoonStatus1 = findViewById(R.id.tv_moon_1);
        tvMoonStatus2 = findViewById(R.id.tv_moon_2);
        tvMoonStatus3 = findViewById(R.id.tv_moon_3);
    }

    public void applyData(List<DailyEntity> dailyEntities, String timeZone) {
        moon1.updateMoon(dailyEntities.get(0).getMp() / 340);
        moon2.updateMoon(dailyEntities.get(7).getMp() / 340);
        moon3.updateMoon(dailyEntities.get(dailyEntities.size()-1).getMp() / 340);

        tvDay1.setText(getContext().getString(R.string.today));
        tvDay2.setText(getContext().getString(R.string.seven_day_next));
        tvDay3.setText(getContext().getString(R.string.fourteen_day_next));


    }

}
