package com.netviet.weathernew;

import android.app.Application;


import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.Arrays;
import java.util.List;


public class WeatherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);

        MobileAds.initialize(this, initializationStatus -> {
        });

        addTestDevice();

    }

    private void addTestDevice(){
        List<String> testDeviceIds = Arrays.asList("33BE2250B43518CCDA7DE426D04EE231");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);
    }
}
