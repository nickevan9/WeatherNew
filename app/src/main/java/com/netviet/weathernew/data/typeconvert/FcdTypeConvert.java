package com.netviet.weathernew.data.typeconvert;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netviet.weathernew.data.model.weather.DailyEntity;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class FcdTypeConvert implements Serializable {

    @TypeConverter // note this annotation
    public String fromOptionValuesList(List<DailyEntity> optionValues) {
        if (optionValues == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<DailyEntity>>() {
        }.getType();
        String json = gson.toJson(optionValues, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<DailyEntity> toOptionValuesList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<DailyEntity>>() {
        }.getType();
        List<DailyEntity> fcdEntities = gson.fromJson(optionValuesString, type);
        return fcdEntities;
    }

}