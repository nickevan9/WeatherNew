package com.netviet.weathernew.data.model.weathersaved;

import com.netviet.weathernew.data.model.air.AirEntity;
import com.netviet.weathernew.data.model.weather.WeatherEntity;

import java.io.Serializable;
import java.util.Date;


public class WeatherDb implements Serializable {
    private String locationName;

    private String cityName ;

    private Double latLocation ;

    private Double lonLocation;

    private Date dateAdded;

    private WeatherEntity weatherEntity;

    private AirEntity airEntity;

    public WeatherDb(String locationName, String cityName, Double latLocation, Double lonLocation, Date dateAdded, WeatherEntity weatherEntity, AirEntity airEntity) {
        this.locationName = locationName;
        this.cityName = cityName;
        this.latLocation = latLocation;
        this.lonLocation = lonLocation;
        this.dateAdded = dateAdded;
        this.weatherEntity = weatherEntity;
        this.airEntity = airEntity;
    }

    public WeatherDb() {

    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLatLocation() {
        return latLocation;
    }

    public void setLatLocation(Double latLocation) {
        this.latLocation = latLocation;
    }

    public Double getLonLocation() {
        return lonLocation;
    }

    public void setLonLocation(Double lonLocation) {
        this.lonLocation = lonLocation;
    }

    public WeatherEntity getWeatherEntity() {
        return weatherEntity;
    }

    public void setWeatherEntity(WeatherEntity weatherEntity) {
        this.weatherEntity = weatherEntity;
    }

    public AirEntity getAirEntity() {
        return airEntity;
    }

    public void setAirEntity(AirEntity airEntity) {
        this.airEntity = airEntity;
    }
}
