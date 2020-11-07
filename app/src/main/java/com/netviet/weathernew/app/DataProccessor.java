package com.netviet.weathernew.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.netviet.weathernew.app.Constant.KEY_FIRST_TIME_LAUNCH;


public class DataProccessor {

    private static Context context;

    public DataProccessor(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "appname_prefs";

    public final static String KEY_LIST_WEATHER = "key_list_weather";

    public boolean sharedPreferenceExist(String key)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        if(!prefs.contains(key)){
            return true;
        }
        else {
            return false;
        }
    }

    public static void setInt( String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"DNF");
    }

    public static void setFirstTimeLaunch( boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_FIRST_TIME_LAUNCH, value);
        editor.apply();
    }

    public static boolean getFirstTimeLaunch() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(KEY_FIRST_TIME_LAUNCH,true);
    }

    public static void setWeatherData(List<WeatherDb> weatherDbList){
        Gson gson = new Gson();
        String jsonCurProduct = gson.toJson(weatherDbList);

        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(KEY_LIST_WEATHER, jsonCurProduct);
        editor.apply();
    }

    public static List<WeatherDb> getWeatherData(){
        Gson gson = new Gson();
        List<WeatherDb> weatherDbs = new ArrayList<>();
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(KEY_LIST_WEATHER, "");

        Type type = new TypeToken<List<WeatherDb>>() {}.getType();
        weatherDbs = gson.fromJson(jsonPreferences, type);

        return weatherDbs;
    }
}