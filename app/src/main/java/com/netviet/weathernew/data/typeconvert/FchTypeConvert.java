package com.netviet.weathernew.data.typeconvert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netviet.weathernew.data.model.weather.HourlyEntity;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;


public class FchTypeConvert implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<HourlyEntity> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<HourlyEntity>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<HourlyEntity> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<HourlyEntity>>() {
        }.getType();
        List<HourlyEntity> fchEntities = gson.fromJson(optionValuesString, type);
        return fchEntities;
    }

}