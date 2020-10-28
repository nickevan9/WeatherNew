package com.netviet.weathernew.data.model.weathersaved;

public class WeatherHourEntity {
    private Double precipitation;
    private Double rainPercent;
    private String windDirection;
    private Double uvIndex;
    private Double cloudCover;
    private Double pressure;
    private String statusCode;
    private Double windSpeed;
    private Double temp;
    private Double visibility;
    private Double tempFeel;
    private String time;
    private Double humidity;
    private String status;

    public WeatherHourEntity() {
    }

    public WeatherHourEntity(Double precipitation, Double rainPercent, String windDirection, Double uvIndex, Double cloudCover, Double pressure, String statusCode, Double windSpeed, Double temp, Double visibility, Double tempFeel, String time, Double humidity, String status) {
        this.precipitation = precipitation;
        this.rainPercent = rainPercent;
        this.windDirection = windDirection;
        this.uvIndex = uvIndex;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.statusCode = statusCode;
        this.windSpeed = windSpeed;
        this.temp = temp;
        this.visibility = visibility;
        this.tempFeel = tempFeel;
        this.time = time;
        this.humidity = humidity;
        this.status = status;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getRainPercent() {
        return rainPercent;
    }

    public void setRainPercent(Double rainPercent) {
        this.rainPercent = rainPercent;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public Double getTempFeel() {
        return tempFeel;
    }

    public void setTempFeel(Double tempFeel) {
        this.tempFeel = tempFeel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
