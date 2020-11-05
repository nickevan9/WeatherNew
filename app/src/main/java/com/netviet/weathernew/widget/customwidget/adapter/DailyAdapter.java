package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;
import com.netviet.weathernew.app.ActivityUtils;
import com.netviet.weathernew.data.model.weather.DailyEntity;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    private List<DailyEntity> dailyEntities;
    private LayoutInflater mInflater;
    private String timeZone;
    private Context context;

    private double min;
    private double max;


    public DailyAdapter(Context context, List<DailyEntity> dailyEntityList, String timeZone) {
        this.dailyEntities = dailyEntityList;
        this.timeZone = timeZone;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_daily, parent, false);
        return new DailyAdapter.ViewHolder(view);
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

    public void applyData(List<DailyEntity> dailyEntities,String timeZone,int max, int min){
        this.dailyEntities = dailyEntities;
        this.timeZone = timeZone;
        this.max = max;
        this.min = min;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTempMax;
        TextView tvTempMin;
        TextView tvRainPercent;
        LinearLayout lnDaily;
        RelativeLayout rlDaily;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTempMax = itemView.findViewById(R.id.tv_temp_max);
            tvTempMin = itemView.findViewById(R.id.tv_temp_min);
            tvRainPercent = itemView.findViewById(R.id.tv_rain_percent);
            lnDaily = itemView.findViewById(R.id.ln_daily);
            rlDaily = itemView.findViewById(R.id.rl_item_daily);
        }

        public void bindItem(DailyEntity dailyEntity){
            tvTempMax.setText(dailyEntity.getTempMax().intValue() + "°");
            tvTempMin.setText(dailyEntity.getTempMin().intValue() + "°");
            float factor = itemView.getContext().getResources().getDisplayMetrics().density;

            int marginLinear = ActivityUtils.convertDpToPixel(context,70);

            LinearLayout.LayoutParams paramLinear = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            paramLinear.height = (int) ((max - min)* 6 * factor + marginLinear);
            lnDaily.setLayoutParams(paramLinear);

            LinearLayout.LayoutParams paramRelative = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            int marginTop = (int)((max - dailyEntity.getTempMax()) * 6 * factor );
            int marginBottom = (int)((dailyEntity.getTempMin() - min) * 6 * factor );
            paramRelative.setMargins(0, marginTop,0, marginBottom);
            rlDaily.setLayoutParams(paramRelative);

            tvRainPercent.setText(dailyEntity.getRainPercent().intValue() + "%");
        }
    }
}
