package com.netviet.weathernew.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.detail.WeatherDetailFragment;

import java.util.List;

public class HomePagerAdapter extends FragmentStateAdapter {
    private List<WeatherDb> weatherDbs;

    public HomePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<WeatherDb> weatherDbList) {
        super(fragmentManager, lifecycle);
        this.weatherDbs = weatherDbList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        WeatherDb weatherDb = weatherDbs.get(position);

        String timeZone = weatherDb.getWeatherEntity().getLoc().getTzname();

        List<HourlyEntity> fchEntityList = TimeUtilsExt.mapTimeToNow(weatherDb.getWeatherEntity().getListHourly(), timeZone);
        List<DailyEntity> fcdEntityList = TimeUtilsExt.mapDateToNow(weatherDb.getWeatherEntity().getListDaily(), timeZone);

        return WeatherDetailFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return weatherDbs.size();
    }

    public void applyData(List<WeatherDb> dbList){
        this.weatherDbs = dbList;
        notifyDataSetChanged();
    }
}
