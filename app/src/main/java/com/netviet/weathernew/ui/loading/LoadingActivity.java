package com.netviet.weathernew.ui.loading;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.netviet.weathernew.R;
import com.netviet.weathernew.app.DataProccessor;
import com.netviet.weathernew.data.model.weathersaved.WeatherDb;
import com.netviet.weathernew.ui.base.BaseActivity;
import com.netviet.weathernew.ui.dialog.LoadingDialog;
import com.netviet.weathernew.ui.home.HomeActivity;

import java.util.List;

public class LoadingActivity extends BaseActivity implements LoadingContract.View {


    private FusedLocationProviderClient mFusedLocationClient;

    private Double latLocation = 0.0;
    private Double lonLocation = 0.0;

    private LoadingDialog loadingDialog;

    public LoadingContract.Presenter loadingPresenter;

    @Override
    protected void initView() {

    }

    @Override
    protected void dataCreate() {
        loadingDialog = new LoadingDialog(this);
        loadingPresenter = new LoadingPresenter(this);

        mFusedLocationClient = new FusedLocationProviderClient(this);

        loadingPresenter.attachView(this);

        if (DataProccessor.getFirstTimeLaunch()) {
            requestPermission();
        } else {
            loadingPresenter.getAllWeather();
        }
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_loading_data;
    }

    public void requestPermission() {
        Dexter.withContext(this).withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ).withListener(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                showLocation();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).withErrorListener(error -> {
            Toast.makeText(this, "Error occurred! $error",
                    Toast.LENGTH_SHORT);

        }).onSameThread().check();
    }


    @SuppressLint("MissingPermission")
    private void showLocation() {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            try {
                this.latLocation = location.getLatitude();
                this.lonLocation = location.getLongitude();

                loadingPresenter.getSingleWeather(latLocation,lonLocation);
            } catch (Exception e) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                DataProccessor.setFirstTimeLaunch(false);
                finish();
            }

        });
    }


    @Override
    public void loadDataSuccess(List<WeatherDb> weatherDbList) {

    }

    @Override
    public void loadDataFailed(String message) {
        loadingDialog.dismissDialog();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        DataProccessor.setFirstTimeLaunch(false);
        finish();
    }

    @Override
    public void loadDataEmpty() {
        if (latLocation != 0.0 && lonLocation != 0.0){
            loadingPresenter.getSingleWeather(latLocation,lonLocation);
        }

    }

    @Override
    public void showLoadingDB() {
        if (loadingDialog.getmDialog().isShowing()){
            loadingDialog.dismissDialog();
            loadingDialog.startLoading(0);
        }else {
            loadingDialog.startLoading(0);
        }
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismissDialog();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        DataProccessor.setFirstTimeLaunch(false);
        finish();
    }

    @Override
    public void showLoadingAPI() {
        if (loadingDialog.getmDialog().isShowing()){
            loadingDialog.dismissDialog();
            loadingDialog.startLoading(1);
        }else {
            loadingDialog.startLoading(1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingPresenter.detachView(this);
        loadingPresenter.destroy();
    }
}