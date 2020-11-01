package com.netviet.weathernew.data.model.weather;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HourlyEntity implements Serializable {
    @SerializedName("pr")
    @Expose
    private Double pr;
    @SerializedName("pp")
    @Expose
    private Double pp;
    @SerializedName("upt")
    @Expose
    private String upt;
    @SerializedName("wn")
    @Expose
    private String windDirection;
    @SerializedName("uv")
    @Expose
    private Double uv;
    @SerializedName("c")
    @Expose
    private Double c;
    @SerializedName("tp")
    @Expose
    private Double tp;
    @SerializedName("p")
    @Expose
    private Double pressure;
    @SerializedName("s")
    @Expose
    private String status;
    @SerializedName("ws")
    @Expose
    private Double windSpeed;
    @SerializedName("t")
    @Expose
    private Double temp;
    @SerializedName("time_tag")
    @Expose
    private Double timeTag;
    @SerializedName("v")
    @Expose
    private Double v;
    @SerializedName("tf")
    @Expose
    private Double tempFeel;
    @SerializedName("dt")
    @Expose
    private String dt;
    @SerializedName("rh")
    @Expose
    private Double rh;
    @SerializedName("td")
    @Expose
    private Double td;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("dts")
    @Expose
    private Double dts;

    public Double getPr() {
        return pr;
    }

    public void setPr(Double pr) {
        this.pr = pr;
    }

    public Double getPp() {
        return pp;
    }

    public void setPp(Double pp) {
        this.pp = pp;
    }

    public String getUpt() {
        return upt;
    }

    public void setUpt(String upt) {
        this.upt = upt;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Double getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(Double timeTag) {
        this.timeTag = timeTag;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Double getTempFeel() {
        return tempFeel;
    }

    public void setTempFeel(Double tempFeel) {
        this.tempFeel = tempFeel;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Double getRh() {
        return rh;
    }

    public void setRh(Double rh) {
        this.rh = rh;
    }

    public Double getTd() {
        return td;
    }

    public void setTd(Double td) {
        this.td = td;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Double getDts() {
        return dts;
    }

    public void setDts(Double dts) {
        this.dts = dts;
    }
}
