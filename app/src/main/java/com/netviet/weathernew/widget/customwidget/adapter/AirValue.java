package com.netviet.weathernew.widget.customwidget.adapter;

public class AirValue {
    private String name;
    private String value;

    public AirValue(){}

    public AirValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
