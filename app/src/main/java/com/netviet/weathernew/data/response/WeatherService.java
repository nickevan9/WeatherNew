package com.netviet.weathernew.data.response;

import com.netviet.weathernew.data.model.weather.WeatherEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("current.php")
    Call<WeatherEntity> getWeatherData(@Query("lat") Double lat, @Query("lon") Double lon);
}
