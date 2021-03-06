package com.netviet.weathernew.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.color.MaterialColors;
import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.widget.customwidget.WidgetAdView;
import com.netviet.weathernew.widget.customwidget.WidgetDetailValue;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherAir;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherDaily;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherDetail;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherHourly;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherMap;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherMoon;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherSun;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherWind;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<WeatherDb> weatherDbs;
    private LayoutInflater mInflater;
    private Context context;

    public WeatherAdapter(Context context, List<WeatherDb> weatherDbs) {
        this.weatherDbs = weatherDbs;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_weather, parent, false);
        return new WeatherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(weatherDbs.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherDbs.size();
    }

    public void applyData(List<WeatherDb> weatherDbList){
        this.weatherDbs = weatherDbList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        NestedScrollView nestedScrollView;
        LinearLayout lnWeather;
        WidgetWeatherDetail wgWeatherDetail;
        WidgetDetailValue wgDetailValue;
        WidgetWeatherAir wgWeatherAir;
        WidgetWeatherHourly wgWeatherHourly;
        WidgetWeatherDaily wgWeatherDaily;
        WidgetAdView wgAdView;
        WidgetWeatherSun wgWeatherSun;
        WidgetWeatherMoon wgWeatherMoon;
        WidgetWeatherWind wgWeatherWind;
        WidgetWeatherMap wgWeatherMap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lnWeather = itemView.findViewById(R.id.ln_weather);
            nestedScrollView = itemView.findViewById(R.id.nested_scroll);
            wgDetailValue = itemView.findViewById(R.id.wg_detail_value);
            wgWeatherDetail = itemView.findViewById(R.id.wg_weather_detail);
            wgWeatherAir = itemView.findViewById(R.id.wg_weather_air);
            wgWeatherHourly = itemView.findViewById(R.id.wg_weather_hourly);
            wgWeatherDaily = itemView.findViewById(R.id.wg_weather_daily);
            wgAdView = itemView.findViewById(R.id.wg_ad_view);
            wgWeatherSun = itemView.findViewById(R.id.wg_weather_sun);
            wgWeatherMoon = itemView.findViewById(R.id.wg_weather_moon);
            wgWeatherWind = itemView.findViewById(R.id.wg_weather_wind);
            wgWeatherMap = itemView.findViewById(R.id.wg_weather_map);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bindItem(WeatherDb weatherDb) {
            String timeZone = weatherDb.getWeatherEntity().getLoc().getTzname();

            List<HourlyEntity> hourlyEntityList = TimeUtilsExt.mapTimeToNow(weatherDb.getWeatherEntity().getListHourly(), timeZone);
            List<DailyEntity> dailyEntityList = TimeUtilsExt.mapDateToNow(weatherDb.getWeatherEntity().getListDaily(), timeZone);
            wgDetailValue.applyData(hourlyEntityList);
            wgWeatherDetail.applyDataDaily(dailyEntityList.get(0));
            wgWeatherDetail.applyDataHourly(hourlyEntityList.get(0));
            wgWeatherDetail.applyName(weatherDb.getLocationName());
            wgWeatherAir.applyData(weatherDb.getAirEntity());
            wgWeatherHourly.applyData(hourlyEntityList,timeZone);
            wgWeatherDaily.applyData(dailyEntityList,timeZone);
            wgWeatherSun.applyData(dailyEntityList,timeZone);
            wgWeatherMoon.applyData(dailyEntityList,timeZone);
            wgWeatherWind.applyData(hourlyEntityList.get(0));

            nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

                if (scrollY > oldScrollY) {
                    RxBus.publish(RxBus.TAG_SWIPE_VIEWPAGER,true);
                    lnWeather.setBackgroundColor(MaterialColors.getColor(context, R.attr.backgroundColorApp, Color.BLACK));
                }
                if (scrollY < oldScrollY) {
                    RxBus.publish(RxBus.TAG_SWIPE_VIEWPAGER,true);
                    lnWeather.setBackgroundColor(MaterialColors.getColor(context, R.attr.backgroundColorApp, Color.BLACK));
                }

                if (scrollY == 0) {
                    RxBus.publish(RxBus.TAG_SWIPE_VIEWPAGER,false);
                    lnWeather.setBackgroundColor(Color.TRANSPARENT); // required to delete elevation shadow
                }
            });
        }
    }
}
