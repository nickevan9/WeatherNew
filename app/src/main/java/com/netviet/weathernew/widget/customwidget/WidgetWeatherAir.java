package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.app.SimpleItemDecorator;
import com.netviet.weathernew.data.model.air.AirEntity;
import com.netviet.weathernew.widget.customview.CustomProgress;
import com.netviet.weathernew.widget.customwidget.adapter.AirDetailAdapter;
import com.netviet.weathernew.widget.customwidget.adapter.AirValue;
import com.netviet.weathernew.widget.customwidget.adapter.LinearLayoutPagerManager;

import java.util.ArrayList;
import java.util.List;

public class WidgetWeatherAir extends ConstraintLayout {

    private ImageView imgAir;
    private TextView tvValueAir;
    private TextView tvAirStatus;
    private CustomProgress pbAir;
    private RecyclerView rvAir;
    private AirDetailAdapter airDetailAdapter;
    private List<AirValue> airValueList;


    public WidgetWeatherAir(Context context) {
        super(context);
        initView();
    }

    public WidgetWeatherAir(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetWeatherAir(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    protected void initView() {

        inflate(getContext(), R.layout.widget_weather_air,this);

        airValueList = new ArrayList<>();

        imgAir = findViewById(R.id.img_air);
        tvValueAir = findViewById(R.id.tv_value_air);
        tvAirStatus = findViewById(R.id.tv_air_status);
        pbAir = findViewById(R.id.pb_air);
        rvAir = findViewById(R.id.rv_air);

        airDetailAdapter = new AirDetailAdapter(getContext(), airValueList);

        LinearLayoutManager layoutPagerManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        rvAir.setLayoutManager(layoutPagerManager);
        rvAir.setHasFixedSize(true);
        rvAir.setAdapter(airDetailAdapter);
        rvAir.addItemDecoration(new SimpleItemDecorator(40,true));

    }

    public void applyData(AirEntity airEntity) {
        int aqi = airEntity.getDataEntity().getAqi();
        tvValueAir.setText(String.valueOf(aqi));
        pbAir.applyData(aqi);

        if (aqi < 50) {
            tvAirStatus.setText(getContext().getString(R.string.excellent_title));
        } else if (aqi < 100) {
            tvAirStatus.setText(getContext().getString(R.string.good_title));
        } else if (aqi < 150) {
            tvAirStatus.setText(getContext().getString(R.string.slight_pollution_title));
        } else if (aqi < 200) {
            tvAirStatus.setText(getContext().getString(R.string.moderate_pollution_title));
        } else if (aqi < 300) {
            tvAirStatus.setText(getContext().getString(R.string.heavily_polluted_title));
        } else {
            tvAirStatus.setText(getContext().getString(R.string.heavy_pollution_title));
        }

        if (!airEntity.getDataEntity().getDetailDensityEntity().getParticulateSmall().equals("-999")) {
            airValueList.add(new AirValue("PM 10", airEntity.getDataEntity().getDetailDensityEntity().getParticulateSmall()));
        } else {
            airValueList.add(new AirValue("PM 10", "0"));
        }

        if (!airEntity.getDataEntity().getDetailDensityEntity().getParticulateBig().equals("-999")) {
            airValueList.add(new AirValue("PM 25", airEntity.getDataEntity().getDetailDensityEntity().getParticulateBig()));
        } else {
            airValueList.add(new AirValue("PM 25", "0"));
        }

        if (!airEntity.getDataEntity().getDetailDensityEntity().getCo().equals("-999")) {
            airValueList.add(new AirValue("CO", airEntity.getDataEntity().getDetailDensityEntity().getCo()));
        } else {
            airValueList.add(new AirValue("CO", "0"));
        }

        if (!airEntity.getDataEntity().getDetailDensityEntity().getNitrogenDioxide().equals("-999")) {
            airValueList.add(new AirValue("NO2", airEntity.getDataEntity().getDetailDensityEntity().getNitrogenDioxide()));
        } else {
            airValueList.add(new AirValue("NO2", "0"));
        }

        if (!airEntity.getDataEntity().getDetailDensityEntity().getSulfurDioxide().equals("-999")){
            airValueList.add(new AirValue("SO2", airEntity.getDataEntity().getDetailDensityEntity().getSulfurDioxide()));
        }else {
            airValueList.add(new AirValue("SO2", "0"));
        }

        if (!airEntity.getDataEntity().getDetailDensityEntity().getOzone().equals("-999")){
            airValueList.add(new AirValue("O3", airEntity.getDataEntity().getDetailDensityEntity().getOzone()));
        }else {
            airValueList.add(new AirValue("O3", "0"));
        }


        airDetailAdapter.applyData(airValueList);
    }


}
