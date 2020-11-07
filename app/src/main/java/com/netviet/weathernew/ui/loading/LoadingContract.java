package com.netviet.weathernew.ui.loading;

import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.base.IBaseController;
import com.netviet.weathernew.ui.base.IBaseView;

import java.util.List;

public class LoadingContract {

    public interface View extends IBaseView {
        void loadDataSuccess(List<WeatherDb> weatherDbList);
        void loadDataFailed(String message);
        void loadDataEmpty();
    }

    public interface Controller extends IBaseController<View> {
        void getAllWeather();

        void getSingleWeather(Double lat,Double lon);
    }
}
