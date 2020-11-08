package com.netviet.weathernew.ui.home;

import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.base.IBaseController;
import com.netviet.weathernew.ui.base.IBaseView;

import java.util.List;

public interface HomeContract {
    public interface View extends IBaseView {
        void loadDataSuccess(List<WeatherDb> weatherDbList, Boolean addWeather);
        void loadDataFailed(String message);
    }

    public interface Controller extends IBaseController<View> {
        void getAllWeather(Boolean addWeather);
        void getSingleWeather(Double lat,Double lon);

    }
}

