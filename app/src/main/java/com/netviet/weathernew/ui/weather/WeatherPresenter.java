package com.netviet.weathernew.ui.weather;

import android.annotation.SuppressLint;
import android.content.Context;

import com.netviet.weathernew.app.DataProccessor;
import com.netviet.weathernew.data.model.air.AirEntity;
import com.netviet.weathernew.data.model.weather.WeatherEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.data.response.AirService;
import com.netviet.weathernew.data.response.ApiClient;
import com.netviet.weathernew.data.response.WeatherService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View mView;

    private Context context;


    public WeatherPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void getAllWeather(Boolean addWeather) {
        mView.showLoadingDB();
        List<WeatherDb> weatherDbs = DataProccessor.getWeatherData();
        mView.loadDataSuccess(weatherDbs,addWeather);

    }

    @Override
    public void getSingleWeather(Double lat, Double lon) {
        mView.showLoadingAPI();

        List<WeatherDb> weatherDbs = DataProccessor.getWeatherData();

        AirService airService = ApiClient.getInstance().getClient2().create(AirService.class);
        WeatherService weatherService = ApiClient.getInstance().getClient().create(WeatherService.class);

        Call<AirEntity> airEntityCall = airService.getAirIndex(lat, lon);
        Call<WeatherEntity> weatherEntityCall = weatherService.getWeatherData(lat, lon);


        weatherEntityCall.enqueue(new Callback<WeatherEntity>() {
            @Override
            public void onResponse(@NotNull Call<WeatherEntity> call, @NotNull Response<WeatherEntity> response) {
                WeatherEntity weatherEntity = response.body();
                airEntityCall.enqueue(new Callback<AirEntity>() {
                    @Override
                    public void onResponse(@NotNull Call<AirEntity> call, @NotNull Response<AirEntity> response) {
                        AirEntity airEntity = response.body();

                        if (containsName(weatherDbs,weatherEntity.getLoc().getName())){
                            mView.loadDataFailed("Data already added");
                        }else {
                            if (weatherEntity.getLoc() != null && airEntity.getDataEntity() != null){
                                WeatherDb weatherDb = createWeather(weatherEntity, airEntity, new Date());

                                List<WeatherDb> weatherDbList = DataProccessor.getWeatherData();
                                weatherDbList.add(weatherDb);

                                DataProccessor.setWeatherData(weatherDbList);
                                mView.loadDataSuccess(weatherDbList,true);
                            }else {
                                mView.loadDataFailed("Empty Data");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AirEntity> call, Throwable t) {
                        mView.loadDataFailed(t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<WeatherEntity> call, Throwable t) {
                mView.loadDataFailed(t.getMessage());
            }
        });
    }

    private WeatherDb createWeather(WeatherEntity weatherEntity, AirEntity airEntity, Date date) {
        WeatherDb weatherDb = new WeatherDb();
        weatherDb.setLocationName(weatherEntity.getLoc().getName());
        weatherDb.setCityName(weatherEntity.getLoc().getName());
        weatherDb.setLatLocation(Double.parseDouble(weatherEntity.getLoc().getLat()));
        weatherDb.setLonLocation(Double.parseDouble(weatherEntity.getLoc().getLon()));
        weatherDb.setWeatherEntity(weatherEntity);
        weatherDb.setAirEntity(airEntity);
        if (date != null) {
            weatherDb.setDateAdded(date);
        } else {
            weatherDb.setDateAdded(new Date());
        }
        return weatherDb;
    }

    @SuppressLint("NewApi")
    public boolean containsName(final List<WeatherDb> list, final String name){
        return list.stream().anyMatch(o -> o.getCityName().equals(name));

    }

    @Override
    public void attachView(WeatherContract.View view) {
        this.mView = view;
        getAllWeather(false);
    }

    @Override
    public void detachView(WeatherContract.View view) {
        this.mView = null;
    }

    @Override
    public void destroy() {

    }
}
