package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.color.MaterialColors;
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

    private int checkedPosition;

    public HourlyAdapter(Context context, List<HourlyEntity> hourlyEntityList, String timeZone) {
        this.hourlyEntities = hourlyEntityList;
        this.timeZone = timeZone;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.checkedPosition = 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_hourly, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!hourlyEntities.isEmpty()) {
            holder.bindItem(hourlyEntities.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        return 14;
    }

    public void applyData(List<HourlyEntity> list,String timeZone){
        this.hourlyEntities = list;
        this.timeZone = timeZone;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardHourly;
        TextView tvTemp;
        ImageView imgHourly;
        TextView tvHour;
        TextView tvRainHour;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardHourly = itemView.findViewById(R.id.card_hourly);
            tvTemp = itemView.findViewById(R.id.tv_temp);
            imgHourly = itemView.findViewById(R.id.img_hourly);
            tvHour = itemView.findViewById(R.id.tv_hour);
            tvRainHour = itemView.findViewById(R.id.tv_rain_hour);
        }

        public void bindItem(HourlyEntity hourlyEntity, int position) {
            if (position == checkedPosition) {
                cardHourly.setCardBackgroundColor(MaterialColors.getColor(context, R.attr.bgCardSelected, Color.BLACK));
                cardHourly.setCardElevation(8);
                cardHourly.setRadius(80);

                tvTemp.setTextColor(MaterialColors.getColor(context, R.attr.textInCard, Color.BLACK));
                tvHour.setTextColor(MaterialColors.getColor(context, R.attr.textInCard, Color.BLACK));
            } else {
                cardHourly.setCardBackgroundColor(ContextCompat.getColor(context, R.color.bg_tranfer_item));
                cardHourly.setCardElevation(0);
                cardHourly.setRadius(0);

                tvHour.setTextColor(Color.parseColor("#8E8E8E"));
                tvTemp.setTextColor(MaterialColors.getColor(context, R.attr.textColorApp, Color.BLACK));
            }

            tvTemp.setText(context.getString(R.string.set_temp, String.valueOf((int) Double.parseDouble(hourlyEntity.getTemp()))));
            imgHourly.setBackgroundResource(IconWeatherHelper.getDrawableAnimation(hourlyEntity.getStatus()));
            AnimationDrawable anim = (AnimationDrawable) imgHourly.getBackground();
            anim.start();
            tvHour.setText(TimeUtilsExt.convertTimeStampToTimeAdapter(hourlyEntity.getDt(), timeZone));
            tvRainHour.setText((int) Double.parseDouble(hourlyEntity.getRh()) + "%");

            itemView.setOnClickListener(view -> {
                checkedPosition = position;
                notifyDataSetChanged();
            });

        }
    }
}
