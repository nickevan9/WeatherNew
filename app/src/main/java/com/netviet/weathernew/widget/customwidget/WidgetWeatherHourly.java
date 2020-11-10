package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.widget.customwidget.adapter.HourlyAdapter;
import com.netviet.weathernew.widget.customwidget.adapter.LinearLayoutPagerManager;

import java.util.ArrayList;
import java.util.List;

public class WidgetWeatherHourly extends RelativeLayout {

    private ImageView imgNextHour;
    private RecyclerView rvHourly;
    private HourlyAdapter hourlyAdapter;
    private List<HourlyEntity> hourlyEntities;
    private String timeZone;

    public WidgetWeatherHourly(Context context) {
        super(context);
        initView();
    }

    public WidgetWeatherHourly(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetWeatherHourly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView(){
        inflate(getContext(), R.layout.widget_weather_hourly,this);
        imgNextHour = findViewById(R.id.img_next_hour);
        rvHourly = findViewById(R.id.rv_next_hour);

        timeZone = "";
        hourlyEntities = new ArrayList<>();

        hourlyAdapter = new HourlyAdapter(getContext(),hourlyEntities,timeZone);

        LinearLayoutPagerManager layoutPagerManager = new LinearLayoutPagerManager(getContext(), LinearLayoutManager.HORIZONTAL, false, 7);

        rvHourly.setLayoutManager(layoutPagerManager);
        rvHourly.setHasFixedSize(true);
        rvHourly.setAdapter(hourlyAdapter);
        rvHourly.setNestedScrollingEnabled(true);
    }

    public void applyData(List<HourlyEntity> fchEntityList,String timeZone){
        hourlyAdapter.applyData(fchEntityList,timeZone);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        RxBus.subscribe(RxBus.TAG_TIME_ZONE,this,timeZoneObject ->{
            this.timeZone = (String)timeZoneObject;
        });

        RxBus.subscribe(RxBus.TAG_LIST_HOUR_ITEM,this, listHourEntity ->{
            List<HourlyEntity> fchEntityList = (List<HourlyEntity>) listHourEntity;
            if (!timeZone.equals("")){
                applyData(fchEntityList,timeZone);
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RxBus.unregister(this);
    }
}
