package com.netviet.weathernew.ui.weather;

import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.base.IBaseController;
import com.netviet.weathernew.ui.base.IBaseView;

import java.util.List;

public interface WeatherContract {
    public interface View extends IBaseView {
        void loadDataSuccess(List<WeatherDb> weatherDbList, Boolean addWeather);
        void loadDataFailed(String message);
        void refreshDataSuccess(List<WeatherDb> weatherDbList);
    }

    public interface Presenter extends IBaseController<View> {
        void getAllWeather(Boolean addWeather);
        void getSingleWeather(Double lat,Double lon);
        void fetchAllWeather(List<WeatherDb> weatherDbs);

    }
}

