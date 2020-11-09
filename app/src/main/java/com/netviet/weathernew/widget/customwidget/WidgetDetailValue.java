package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.widget.customwidget.adapter.DetailValue;
import com.netviet.weathernew.widget.customwidget.adapter.DetailValueAdapter;
import com.netviet.weathernew.widget.customwidget.adapter.LinearLayoutPagerManager;

import java.util.ArrayList;
import java.util.List;

public class WidgetDetailValue extends RelativeLayout {

    private RecyclerView rvDetailValue;
    private DetailValueAdapter detailValueAdapter;
    private List<DetailValue> detailValues;

    public WidgetDetailValue(Context context) {
        super(context);
    }

    public WidgetDetailValue(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetDetailValue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView() {
        inflate(getContext(), R.layout.widget_detail_value, this);
        detailValues = new ArrayList<>();
        rvDetailValue = findViewById(R.id.rv_weather_detail);

        detailValueAdapter = new DetailValueAdapter(getContext(), detailValues);


        LinearLayoutPagerManager layoutPagerManager = new LinearLayoutPagerManager(getContext(), LinearLayoutManager.HORIZONTAL, false, 4);

        rvDetailValue.setLayoutManager(layoutPagerManager);
        rvDetailValue.setHasFixedSize(true);
        rvDetailValue.setAdapter(detailValueAdapter);
    }

    private void applyData(List<HourlyEntity> hourlyEntityList) {
        HourlyEntity hourlyEntity = hourlyEntityList.get(0);

        detailValues.add(new DetailValue(getContext().getString(R.string.humidity), hourlyEntity.getRh() + " %", R.drawable.ic_humidity));
        detailValues.add(new DetailValue(getContext().getString(R.string.visibility), hourlyEntity.getV() + " km", R.drawable.ic_vision));
        detailValues.add(new DetailValue(getContext().getString(R.string.uv_index), hourlyEntity.getUv().toString(), R.drawable.ic_uv));
        detailValues.add(new DetailValue(getContext().getString(R.string.dewpoint), hourlyEntity.getV() + " %", R.drawable.ic_dewpoint));

        detailValueAdapter.applyData(detailValues);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();


        RxBus.subscribe(RxBus.TAG_LIST_HOUR_ITEM, this, listHourEntity -> {
            List<HourlyEntity> fchEntityList = (List<HourlyEntity>) listHourEntity;
            applyData(fchEntityList);
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RxBus.unregister(this);
    }
}
