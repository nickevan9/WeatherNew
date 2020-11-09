package com.netviet.weathernew.data.model.weather;


import androidx.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherEntity {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("d_id")
    @Expose
    private Double dId;
    @Embedded
    @SerializedName("warning")
    @Expose
    private WarningEntity warning;
    @Embedded
    @SerializedName("cc")
    @Expose
    private CcEntity cc;
    @SerializedName("fch")
    @Expose
    private List<HourlyEntity> listHourly = null;
    @SerializedName("fcd")
    @Expose
    private List<DailyEntity> listDaily = null;
    @Embedded
    @SerializedName("loc")
    @Expose
    private LocEntity loc;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("update")
    @Expose
    private Double update;
    @Embedded
    @SerializedName("extra")
    @Expose
    private ExtraEntity extra;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDId() {
        return dId;
    }

    public void setDId(Double dId) {
        this.dId = dId;
    }

    public WarningEntity getWarning() {
        return warning;
    }

    public void setWarning(WarningEntity warning) {
        this.warning = warning;
    }

    public CcEntity getCc() {
        return cc;
    }

    public void setCc(CcEntity cc) {
        this.cc = cc;
    }

    public List<HourlyEntity> getListHourly() {
        return listHourly;
    }

    public void setListHourly(List<HourlyEntity> listHourly) {
        this.listHourly = listHourly;
    }

    public List<DailyEntity> getListDaily() {
        return listDaily;
    }

    public void setListDaily(List<DailyEntity> listDaily) {
        this.listDaily = listDaily;
    }

    public LocEntity getLoc() {
        return loc;
    }

    public void setLoc(LocEntity loc) {
        this.loc = loc;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Double getUpdate() {
        return update;
    }

    public void setUpdate(Double update) {
        this.update = update;
    }

    public ExtraEntity getExtra() {
        return extra;
    }

    public void setExtra(ExtraEntity extra) {
        this.extra = extra;
    }

}
