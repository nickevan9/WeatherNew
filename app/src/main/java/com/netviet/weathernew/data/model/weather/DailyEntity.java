package com.netviet.weathernew.data.model.weather;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DailyEntity implements Serializable {
    @SerializedName("pr")
    @Expose
    private String pr;
    @SerializedName("pp")
    @Expose
    private String rainPercent;
    @SerializedName("set")
    @Expose
    private String set;
    @SerializedName("tx")
    @Expose
    private String tempMax;
    @SerializedName("wn")
    @Expose
    private String wn;
    @SerializedName("ca")
    @Expose
    private String ca;
    @SerializedName("rise")
    @Expose
    private String rise;
    @SerializedName("dl")
    @Expose
    private String dl;
    @SerializedName("tp")
    @Expose
    private String tp;
    @SerializedName("tn")
    @Expose
    private String tempMin;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("s")
    @Expose
    private String s;
    @SerializedName("mrise")
    @Expose
    private String mrise;
    @SerializedName("rh_avg")
    @Expose
    private String rhAvg;
    @SerializedName("time_tag")
    @Expose
    private String timeTag;
    @SerializedName("wsx")
    @Expose
    private String wsx;
    @SerializedName("dt")
    @Expose
    private String dt;
    @SerializedName("mset")
    @Expose
    private String mset;
    @SerializedName("upt")
    @Expose
    private String upt;
    @SerializedName("uv")
    @Expose
    private String uv;
    @SerializedName("mp")
    @Expose
    private String mp;
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

    public String getRainPercent() {
        return rainPercent;
    }

    public void setRainPercent(String rainPercent) {
        this.rainPercent = rainPercent;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getWn() {
        return wn;
    }

    public void setWn(String wn) {
        this.wn = wn;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getMrise() {
        return mrise;
    }

    public void setMrise(String mrise) {
        this.mrise = mrise;
    }

    public String getRhAvg() {
        return rhAvg;
    }

    public void setRhAvg(String rhAvg) {
        this.rhAvg = rhAvg;
    }

    public String getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
    }

    public String getWsx() {
        return wsx;
    }

    public void setWsx(String wsx) {
        this.wsx = wsx;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getMset() {
        return mset;
    }

    public void setMset(String mset) {
        this.mset = mset;
    }

    public String getUpt() {
        return upt;
    }

    public void setUpt(String upt) {
        this.upt = upt;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
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
