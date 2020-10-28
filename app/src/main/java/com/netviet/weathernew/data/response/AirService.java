package com.netviet.weathernew.data.response;

import com.netviet.weathernew.data.model.air.AirEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirService {
    @GET("query.php")
    Call<AirEntity> getAirIndex(@Query("lat") Double lat, @Query("lng") Double lon);
}
