package com.netviet.weathernew.data.model.weathersaved;

import java.util.Date;

public class WeatherAirSaved {
    private String locationName;

    private String cityName ;

    private Double latLocation ;

    private Double lonLocation;

    private Date dateAdded;

    private WeatherSaved weatherSaved;

    private AirSaved airSaved;

    public WeatherAirSaved() {
    }

    public WeatherAirSaved(String locationName, String cityName, Double latLocation, Double lonLocation, Date dateAdded, WeatherSaved weatherSaved, AirSaved airSaved) {
        this.locationName = locationName;
        this.cityName = cityName;
        this.latLocation = latLocation;
        this.lonLocation = lonLocation;
        this.dateAdded = dateAdded;
        this.weatherSaved = weatherSaved;
        this.airSaved = airSaved;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public WeatherSaved getWeatherSaved() {
        return weatherSaved;
    }

    public void setWeatherSaved(WeatherSaved weatherSaved) {
        this.weatherSaved = weatherSaved;
    }

    public AirSaved getAirSaved() {
        return airSaved;
    }

    public void setAirSaved(AirSaved airSaved) {
        this.airSaved = airSaved;
    }
}
