package com.netviet.weathernew.data.model.weather;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HourlyEntity implements Serializable {
    @SerializedName("pr")
    @Expose
    private String pr;
    @SerializedName("pp")
    @Expose
    private String pp;
    @SerializedName("upt")
    @Expose
    private String upt;
    @SerializedName("wn")
    @Expose
    private String windDirection;
    @SerializedName("uv")
    @Expose
    private String uv;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("tp")
    @Expose
    private String tp;
    @SerializedName("p")
    @Expose
    private String pressure;
    @SerializedName("s")
    @Expose
    private String status;
    @SerializedName("ws")
    @Expose
    private String windSpeed;
    @SerializedName("t")
    @Expose
    private String temp;
    @SerializedName("time_tag")
    @Expose
    private String timeTag;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("tf")
    @Expose
    private String tempFeel;
    @SerializedName("dt")
    @Expose
    private String dt;
    @SerializedName("rh")
    @Expose
    private String rh;
    @SerializedName("td")
    @Expose
    private String td;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("dts")
    @Expose
    private String dts;

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
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

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getTempFeel() {
        return tempFeel;
    }

    public void setTempFeel(String tempFeel) {
        this.tempFeel = tempFeel;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getDts() {
        return dts;
    }

    public void setDts(String dts) {
        this.dts = dts;
    }
}
