package com.netviet.weathernew.data.model.weather;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CcEntity {
    @SerializedName("pr")
    @Expose
    private String pr;
    @SerializedName("prestend")
    @Expose
    private String prestend;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("dist")
    @Expose
    private String dist;
    @SerializedName("wn")
    @Expose
    private String wn;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("s")
    @Expose
    private String s;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("ws")
    @Expose
    private String ws;
    @SerializedName("t")
    @Expose
    private String t;
    @SerializedName("time_tag")
    @Expose
    private String timeTag;
    @SerializedName("v")
    @Expose
    private String v;
    @SerializedName("tf")
    @Expose
    private String tf;
    @SerializedName("dt")
    @Expose
    private String dt;
    @SerializedName("rh")
    @Expose
    private String rh;
    @SerializedName("td")
    @Expose
    private String td;
    @SerializedName("uv")
    @Expose
    private String uv;
    @SerializedName("txt")
    @Expose
    private String txt;
    @SerializedName("dts")
    @Expose
    private Double dts;

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getPrestend() {
        return prestend;
    }

    public void setPrestend(String prestend) {
        this.prestend = prestend;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getWn() {
        return wn;
    }

    public void setWn(String wn) {
        this.wn = wn;
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
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

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
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

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
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
