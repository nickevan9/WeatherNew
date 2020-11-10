package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.IconWeatherHelper;
import com.netviet.weathernew.app.TimeUtilsExt;
import com.netviet.weathernew.data.model.weather.DailyEntity;

import java.util.List;

public class DailyDayAdapter extends  RecyclerView.Adapter<DailyDayAdapter.ViewHolder> {

    private List<DailyEntity> dailyEntities;
    private LayoutInflater mInflater;
    private String timeZone;
    private Context context;

    public DailyDayAdapter(Context context, List<DailyEntity> dailyEntityList, String timeZone) {
        this.dailyEntities = dailyEntityList;
        this.timeZone = timeZone;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_daily_card, parent, false);
        return new DailyDayAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!dailyEntities.isEmpty()){
            DailyEntity dailyEntity = dailyEntities.get(position);
            holder.bindItem(dailyEntity);
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public void applyData(List<DailyEntity> dailyEntities,String timeZone){
        this.dailyEntities = dailyEntities;
        this.timeZone = timeZone;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgWeatherStatus;
        TextView tvDayOfWeek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWeatherStatus = itemView.findViewById(R.id.img_weather_status);
            tvDayOfWeek = itemView.findViewById(R.id.tv_day_week);
        }

        public void bindItem(DailyEntity dailyEntity){
            imgWeatherStatus.setBackgroundResource(IconWeatherHelper.getDrawableAnimation(dailyEntity.getS()));
            AnimationDrawable anim = (AnimationDrawable) imgWeatherStatus.getBackground();
            anim.start();

            tvDayOfWeek.setText(TimeUtilsExt.convertTimeToDayOfWeek(dailyEntity.getDt(), timeZone));
        }
    }
}
