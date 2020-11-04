package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.IconWeatherHelper;
import com.netviet.weathernew.app.TimeUtilsExt;
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
        if (position == 0) {
            holder.bindItem(hourlyEntities.get(position), true);
        } else {
            holder.bindItem(hourlyEntities.get(position), false);
        }
    }

    @Override
    public int getItemCount() {
        return hourlyEntities.size();
    }

    public void applyData(List<HourlyEntity> list,String timeZone){
        this.hourlyEntities = list;
        this.timeZone = timeZone;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardHourly;
        private TextView tvTemp;
        private ImageView imgHourly;
        private TextView tvHour;
        private TextView tvRainHour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindItem(HourlyEntity hourlyEntity, Boolean firstPosition) {
            if (firstPosition) {
                cardHourly.setCardBackgroundColor(R.attr.bgCardSelected);
                cardHourly.setCardElevation(8);
                cardHourly.setRadius(8);
                tvTemp.setTextColor(R.attr.textInCard);
                tvHour.setTextColor(R.attr.textInCard);
            }

            tvTemp.setText(context.getString(R.string.set_temp, String.valueOf(hourlyEntity.getTemp().intValue())));
            ;

            imgHourly.setBackgroundResource(IconWeatherHelper.getDrawableAnimation(hourlyEntity.getStatus()));
            AnimationDrawable anim = (AnimationDrawable) imgHourly.getBackground();
            anim.start();

            tvHour.setText(TimeUtilsExt.convertTimeStampToTime12Hour(hourlyEntity.getDt(), timeZone));

            tvRainHour.setText(String.valueOf(hourlyEntity.getRh()));

        }
    }
}
