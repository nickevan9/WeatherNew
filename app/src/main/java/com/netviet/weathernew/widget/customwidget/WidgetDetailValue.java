package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.SimpleItemDecorator;
import com.netviet.weathernew.data.model.weather.HourlyEntity;
import com.netviet.weathernew.widget.customwidget.adapter.DetailValue;
import com.netviet.weathernew.widget.customwidget.adapter.DetailValueAdapter;

import java.util.ArrayList;
import java.util.List;

public class WidgetDetailValue extends RelativeLayout {

    private RecyclerView rvDetailValue;
    private DetailValueAdapter detailValueAdapter;
    private List<DetailValue> detailValues;

    public WidgetDetailValue(Context context) {
        super(context);
        initView();
    }

    public WidgetDetailValue(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WidgetDetailValue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView() {
        inflate(getContext(), R.layout.widget_detail_value, this);
        detailValues = new ArrayList<>();
        rvDetailValue = findViewById(R.id.rv_weather_detail);

        detailValueAdapter = new DetailValueAdapter(getContext(), detailValues);


        GridLayoutManager layoutPagerManager = new GridLayoutManager(getContext(),4);

        rvDetailValue.setLayoutManager(layoutPagerManager);
        rvDetailValue.setHasFixedSize(true);
        rvDetailValue.setAdapter(detailValueAdapter);
        rvDetailValue.addItemDecoration(new SimpleItemDecorator(40,true));
    }

    public void applyData(List<HourlyEntity> hourlyEntityList) {
        HourlyEntity hourlyEntity = hourlyEntityList.get(0);

        detailValues.clear();

        detailValues.add(new DetailValue(getContext().getString(R.string.humidity), (int) Double.parseDouble(hourlyEntity.getPp()) + " %", R.drawable.ic_humidity));
        detailValues.add(new DetailValue(getContext().getString(R.string.visibility), (int) Double.parseDouble(hourlyEntity.getV()) / 1000 + " km", R.drawable.ic_vision));
        detailValues.add(new DetailValue(getContext().getString(R.string.uv_index), String.valueOf((int) Double.parseDouble(hourlyEntity.getUv())), R.drawable.ic_uv));
        detailValues.add(new DetailValue(getContext().getString(R.string.dewpoint), (int) Double.parseDouble(hourlyEntity.getRh()) + " %", R.drawable.ic_dewpoint));

        detailValueAdapter.applyData(detailValues);
    }


}
