package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.widget.customwidget.adapter.DailyAdapter;
import com.netviet.weathernew.widget.customwidget.adapter.DailyDayAdapter;
import com.netviet.weathernew.widget.customwidget.adapter.LinearLayoutPagerManager;

import java.util.ArrayList;
import java.util.List;

public class WidgetWeatherDaily extends RelativeLayout {
    private ImageView imgNextDay;
    private RecyclerView rvNextDay;
    private RecyclerView rvCardNextDay;

    private DailyAdapter dailyAdapter;
    private DailyDayAdapter dailyDayAdapter;

    private List<DailyEntity> dailyEntities;
    private String timeZone;

    private int max;
    private int min;

    public WidgetWeatherDaily(Context context) {
        super(context);
        initView();
    }

    public WidgetWeatherDaily(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetWeatherDaily(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView(){
        inflate(getContext(), R.layout.widget_weather_daily,this);
        dailyEntities = new ArrayList<>();
        timeZone = "";

        imgNextDay = findViewById(R.id.img_next_day);
        rvNextDay = findViewById(R.id.rv_next_day);
        rvCardNextDay = findViewById(R.id.rv_card_next_day);

        dailyAdapter = new DailyAdapter(getContext(),dailyEntities,timeZone);
        dailyDayAdapter = new DailyDayAdapter(getContext(),dailyEntities,timeZone);

        LinearLayoutPagerManager layoutPagerManager = new LinearLayoutPagerManager(getContext(), LinearLayoutManager.HORIZONTAL, false, 7);

        LinearLayoutPagerManager layoutPagerManagerCard = new LinearLayoutPagerManager(getContext(), LinearLayoutManager.HORIZONTAL, false, 7);


        rvNextDay.setLayoutManager(layoutPagerManager);
        rvNextDay.setHasFixedSize(true);
        rvNextDay.setAdapter(dailyAdapter);
        rvNextDay.setNestedScrollingEnabled(true);

        rvCardNextDay.setLayoutManager(layoutPagerManagerCard);
        rvCardNextDay.setHasFixedSize(true);
        rvCardNextDay.setAdapter(dailyDayAdapter);
        rvNextDay.setNestedScrollingEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void applyData(List<DailyEntity> dailyEntities, String timeZone) {
        max = (int) dailyEntities.stream().mapToDouble(DailyEntity::getTempMax).max().getAsDouble();
        min = (int) dailyEntities.stream().mapToDouble(DailyEntity::getTempMin).min().getAsDouble();
        dailyAdapter.applyData(dailyEntities,timeZone,max,min);
        dailyDayAdapter.applyData(dailyEntities,timeZone);
    }


}
