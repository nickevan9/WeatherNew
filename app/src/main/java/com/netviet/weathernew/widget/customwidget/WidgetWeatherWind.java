package com.netviet.weathernew.widget.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.RxBus;
import com.netviet.weathernew.app.WindConvert;
import com.netviet.weathernew.data.model.weather.HourlyEntity;

public class WidgetWeatherWind extends ConstraintLayout {

    private ImageView imgWind;
    private TextView tvWindDirection;
    private TextView tvWindSpeed;
    private TextView tvLevelWind;
    private TextView tvPressure;

    private double windSpeed = 1.0D;


    public WidgetWeatherWind(@NonNull Context context) {
        super(context);
    }

    public WidgetWeatherWind(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetWeatherWind(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initView() {
        inflate(getContext(), R.layout.widget_weather_wind, this);

        imgWind = findViewById(R.id.img_wind);
        tvWindDirection = findViewById(R.id.tv_wind_direction);
        tvWindSpeed = findViewById(R.id.tv_wind_speed);
        tvLevelWind = findViewById(R.id.tv_level_wind);
        tvPressure = findViewById(R.id.tv_pressure);

    }


    private void applyData(HourlyEntity hourlyEntity) {
        tvWindDirection.setText(WindConvert.convertWindDirection(hourlyEntity.getWindDirection()));
        tvWindSpeed.setText(getContext().getString(R.string.set_speed, hourlyEntity.getWindSpeed().toString()));
        windSpeed = hourlyEntity.getWindSpeed();

        if (windSpeed < 2) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,1));
        } else if (windSpeed < 3.5) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,2));
        } else if (windSpeed < 5.5) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,3));
        } else if (windSpeed < 8.5) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,4));
        } else if (windSpeed < 11) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,5));
        } else if (windSpeed < 14) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,6));
        } else if (windSpeed < 17) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,7));
        } else if (windSpeed < 20.5) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,8));
        } else if (windSpeed < 24) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,9));
        } else if (windSpeed < 28) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,10));
        } else if (windSpeed < 32) {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,11));
        } else {
            tvLevelWind.setText( getContext().getString(R.string.wind_level,12));
        }

        tvPressure.setText(getContext().getString(R.string.set_pressure,hourlyEntity.getPressure()));

        rotateWindSpeed();
    }

    private void rotateWindSpeed() {
        if (windSpeed <= 0.0D)
            windSpeed = 1.0D;
        double d1 = 10000;
        double d2 = windSpeed;
        Double.isNaN(d1);
        long duration = (long) (d1 / d2);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        imgWind.startAnimation(rotateAnimation);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RxBus.subscribe(RxBus.TAG_HOUR_ITEM, this, hourlyObject -> {
            HourlyEntity hourlyEntity = (HourlyEntity) hourlyObject;
            applyData(hourlyEntity);

        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RxBus.unregister(this);
    }
}
