package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.data.model.weather.HourlyEntity;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {

    private List<HourlyEntity> hourlyEntities;
    private LayoutInflater mInflater;
    private String timeZone;
    private Context context;


    public HourlyAdapter(Context context, List<HourlyEntity> hourlyEntityList, String timeZone) {
        this.hourlyEntities = hourlyEntityList;
        this.timeZone = timeZone;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_hourly, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardHourly;
        private TextView tvTemp;
        private ImageView imgHourly;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
