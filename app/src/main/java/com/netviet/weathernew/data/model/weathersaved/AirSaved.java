package com.netviet.weathernew.data.model.weathersaved;

public class AirSaved {
    private String statusAir;

    private Integer aqi;

    //pm25
    private String particulateBigAqi;

    //pm10
    private String particulateSmallAqi;

    //co
    private String coAqi;

    //so2
    private String sulfurDioxideAqi;

    //o3
    private String ozoneAqi;

    //no2
    private String nitrogenDioxideAqi;

    public AirSaved() {
    }

    public AirSaved(String statusAir, Integer aqi, String particulateBigAqi, String particulateSmallAqi, String coAqi, String sulfurDioxideAqi, String ozoneAqi, String nitrogenDioxideAqi) {
        this.statusAir = statusAir;
        this.aqi = aqi;
        this.particulateBigAqi = particulateBigAqi;
        this.particulateSmallAqi = particulateSmallAqi;
        this.coAqi = coAqi;
        this.sulfurDioxideAqi = sulfurDioxideAqi;
        this.ozoneAqi = ozoneAqi;
        this.nitrogenDioxideAqi = nitrogenDioxideAqi;
    }

    public String getStatusAir() {
        return statusAir;
    }

    public void setStatusAir(String statusAir) {
        this.statusAir = statusAir;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public String getParticulateBigAqi() {
        return particulateBigAqi;
    }

    public void setParticulateBigAqi(String particulateBigAqi) {
        this.particulateBigAqi = particulateBigAqi;
    }

    public String getParticulateSmallAqi() {
        return particulateSmallAqi;
    }

    public void setParticulateSmallAqi(String particulateSmallAqi) {
        this.particulateSmallAqi = particulateSmallAqi;
    }

    public String getCoAqi() {
        return coAqi;
    }

    public void setCoAqi(String coAqi) {
        this.coAqi = coAqi;
    }

    public String getSulfurDioxideAqi() {
        return sulfurDioxideAqi;
    }

    public void setSulfurDioxideAqi(String sulfurDioxideAqi) {
        this.sulfurDioxideAqi = sulfurDioxideAqi;
    }

    public String getOzoneAqi() {
        return ozoneAqi;
    }

    public void setOzoneAqi(String ozoneAqi) {
        this.ozoneAqi = ozoneAqi;
    }

    public String getNitrogenDioxideAqi() {
        return nitrogenDioxideAqi;
    }

    public void setNitrogenDioxideAqi(String nitrogenDioxideAqi) {
        this.nitrogenDioxideAqi = nitrogenDioxideAqi;
    }
}
