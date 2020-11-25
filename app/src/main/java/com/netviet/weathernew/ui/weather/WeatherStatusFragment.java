package com.netviet.weathernew.ui.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netviet.weathernew.R;
import com.netviet.weathernew.data.model.weather.DailyEntity;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.widget.customwidget.WidgetDetailValue;
import com.netviet.weathernew.widget.customwidget.WidgetWeatherDetail;


public class WeatherStatusFragment extends Fragment {


    private static final String ARG_PARAM_FCH = "fch_entity";
    private static final String ARG_PARAM_FCD = "fcd_entity";
    private static final String ARG_PARAM_NAME = "time_zone";

    // TODO: Rename and change types of parameters
    private HourlyEntity hourlyEntity;
    private DailyEntity dailyEntity;
    private String name;

    private WidgetWeatherDetail widgetWeatherDetail;
    private WidgetDetailValue widgetDetailValue;

    public WeatherStatusFragment() {
        // Required empty public constructor
    }


    public static WeatherStatusFragment newInstance(HourlyEntity hourlyEntity, DailyEntity dailyEntity, String name) {
        WeatherStatusFragment fragment = new WeatherStatusFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_FCH, hourlyEntity);
        args.putSerializable(ARG_PARAM_FCD, dailyEntity);
        args.putString(ARG_PARAM_NAME,name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hourlyEntity = (HourlyEntity) getArguments().getSerializable(ARG_PARAM_FCH);
            dailyEntity = (DailyEntity) getArguments().getSerializable(ARG_PARAM_FCD);
            name = getArguments().getString(ARG_PARAM_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        widgetWeatherDetail = view.findViewById(R.id.wg_weather_detail);
        widgetDetailValue = view.findViewById(R.id.wg_detail_value);


        widgetWeatherDetail.applyDataHourly(hourlyEntity);
        widgetWeatherDetail.applyDataDaily(dailyEntity);
        widgetWeatherDetail.applyName(name);


    }
}