package com.netviet.weathernew.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.DataProccessor;
import com.netviet.weathernew.ui.loading.LoadingActivity;

public class SplashActivity extends AppCompatActivity {

    private DataProccessor dataProccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}