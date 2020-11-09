package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
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

        imgAir = findViewById(R.id.img_air);
        tvValueAir = findViewById(R.id.tv_value_air);
        tvAirStatus = findViewById(R.id.tv_air_status);
        pbAir = findViewById(R.id.pb_air);
        rvAir = findViewById(R.id.rv_air);

        airValueList = new ArrayList<>();

        airDetailAdapter = new AirDetailAdapter(getContext(), airValueList);

        LinearLayoutPagerManager layoutPagerManager = new LinearLayoutPagerManager(getContext(), LinearLayoutManager.HORIZONTAL, false, 5);

        rvAir.setLayoutManager(layoutPagerManager);
        rvAir.setHasFixedSize(true);
        rvAir.setAdapter(airDetailAdapter);

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

        airValueList.add(new AirValue("PM 10",airEntity.getDataEntity().getDetailAqiEntity().getParticulateSmallAqi()));

        airValueList.add(new AirValue("PM 25",airEntity.getDataEntity().getDetailAqiEntity().getParticulateBigAqi()));

        airValueList.add(new AirValue("CO",airEntity.getDataEntity().getDetailAqiEntity().getCoAqi()));

        airValueList.add(new AirValue("NO2",airEntity.getDataEntity().getDetailAqiEntity().getNitrogenDioxideAqi()));

        airValueList.add(new AirValue("SO2",airEntity.getDataEntity().getDetailAqiEntity().getSulfurDioxideAqi()));

        airValueList.add(new AirValue("O3",airEntity.getDataEntity().getDetailAqiEntity().getOzoneAqi()));

        airDetailAdapter.applyData(airValueList);


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RxBus.subscribe(RxBus.TAG_AIR_WEATHER, this, airObject -> {
            AirEntity airEntity = (AirEntity) airObject;
            applyData(airEntity);
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RxBus.unregister(this);
    }
}
