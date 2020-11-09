package com.netviet.weathernew.widget.customwidget.adapter;

public class DetailValue {
    private String name ;
    private String value;
    private int drawable;

    public DetailValue(){}

    public DetailValue(String name, String value, int drawable) {
        this.name = name;
        this.value = value;
        this.drawable = drawable;
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

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
