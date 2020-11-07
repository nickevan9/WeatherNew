package com.netviet.weathernew.ui.loading;

import android.content.Context;

import com.netviet.weathernew.app.DataProccessor;
import com.netviet.weathernew.data.model.air.AirEntity;
import com.netviet.weathernew.data.model.weather.WeatherEntity;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.data.response.AirService;
import com.netviet.weathernew.data.response.ApiClient;
import com.netviet.weathernew.data.response.WeatherService;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingPresenter implements LoadingContract.Controller {
    private LoadingContract.View mView;
    private Context context;
    private DataProccessor dataProccessor;

    public LoadingPresenter(Context context) {
        this.context = context;
        dataProccessor = new DataProccessor(context);
    }


    @Override
    public void getAllWeather() {
        mView.showLoadingDB();
        List<WeatherDb> weatherDbs = DataProccessor.getWeatherData();
        if (weatherDbs.isEmpty()) {
            mView.loadDataEmpty();
        } else {

        }
    }


    public void fetchAllWeather(List<WeatherDb> weatherDbs) {
        for (int index = 0; index < weatherDbs.size(); index++) {
            Double lat = weatherDbs.get(index).getLatLocation();
            Double lon = weatherDbs.get(index).getLonLocation();

            AirService airService = ApiClient.getInstance().getClient2().create(AirService.class);
            WeatherService weatherService = ApiClient.getInstance().getClient().create(WeatherService.class);

            Call<AirEntity> airEntityCall = airService.getAirIndex(lat, lon);
            Call<WeatherEntity> weatherEntityCall = weatherService.getWeatherData(lat, lon);

            int finalIndex = index;

            weatherEntityCall.enqueue(new Callback<WeatherEntity>() {
                @Override
                public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                    WeatherEntity weatherEntity = response.body();
                    airEntityCall.enqueue(new Callback<AirEntity>() {
                        @Override
                        public void onResponse(Call<AirEntity> call, Response<AirEntity> response) {
                            AirEntity airEntity = response.body();

                            WeatherDb weatherDb = createWeather(weatherEntity, airEntity, new Date());

                            if (finalIndex == (weatherDbs.size() - 1)) {
//                                insertToDb(weatherDb, true);
                            } else {
//                                insertToDb(weatherDb, false);
                            }

                        }

                        @Override
                        public void onFailure(Call<AirEntity> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<WeatherEntity> call, Throwable t) {

                }
            });


        }
    }

    private WeatherDb createWeather(WeatherEntity weatherEntity, AirEntity airEntity, Date date) {
        WeatherDb weatherDb = new WeatherDb();
        weatherDb.setLocationName(weatherEntity.getLoc().getName());
        weatherDb.setCityName(weatherEntity.getLoc().getName());
        weatherDb.setLatLocation(weatherEntity.getLoc().getLat());
        weatherDb.setLonLocation(weatherEntity.getLoc().getLon());
        weatherDb.setWeatherEntity(weatherEntity);
        weatherDb.setAirEntity(airEntity);
        if (date != null) {
            weatherDb.setDateAdded(date);
        } else {
            weatherDb.setDateAdded(new Date());
        }
        return weatherDb;
    }

    @Override
    public void getSingleWeather(Double lat, Double lon) {
        mView.showLoadingAPI();
    }

    @Override
    public void attachView(LoadingContract.View view) {

    }

    @Override
    public void detachView(LoadingContract.View view) {

    }

    @Override
    public void destroy() {

    }
}
