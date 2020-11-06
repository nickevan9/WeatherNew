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
    }

    public WidgetWeatherMoon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetWeatherMoon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    private void applyData(List<DailyEntity> dailyEntities, String timeZone) {
        moon1.updateMoon(dailyEntities.get(0).getMp() / 340);
        moon1.updateMoon(dailyEntities.get(7).getMp() / 340);
        moon1.updateMoon(dailyEntities.get(13).getMp() / 340);

        tvDay1.setText(getContext().getString(R.string.today));
        tvDay2.setText(getContext().getString(R.string.seven_day_next));
        tvDay2.setText(getContext().getString(R.string.fourteen_day_next));


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RxBus.subscribe(RxBus.TAG_TIME_ZONE, this, timeZoneObject -> {
            this.timeZone = (String) timeZoneObject;
        });

        RxBus.subscribe(RxBus.TAG_LIST_DAY_ITEM, this, listDayObject -> {
            List<DailyEntity> dailyEntities = (List<DailyEntity>) listDayObject;
            if (!timeZone.equals("")) {
                applyData(dailyEntities, timeZone);
            }
        });

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RxBus.unregister(this);
    }
}
