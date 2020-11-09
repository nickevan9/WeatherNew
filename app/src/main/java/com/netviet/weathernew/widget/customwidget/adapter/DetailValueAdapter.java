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

import java.util.List;

public class DetailValueAdapter extends RecyclerView.Adapter<DetailValueAdapter.ViewHolder> {

    private List<DetailValue> valueList;
    private LayoutInflater mInflater;
    private Context context;


    public DetailValueAdapter(Context context,List<DetailValue> detailValues){
        this.valueList = detailValues;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_weather_detail, parent, false);
        return new DetailValueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(valueList.get(position));
    }

    public void applyData(List<DetailValue> detailValues){
        this.valueList = detailValues;
    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgDetailValue;
        TextView tvDetailValue;
        TextView tvNameValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDetailValue = itemView.findViewById(R.id.img_detail_value);
            tvDetailValue = itemView.findViewById(R.id.tv_detail_value);
            tvNameValue = itemView.findViewById(R.id.tv_detail_name);
        }

        public void bindItem(DetailValue detailValue){
            imgDetailValue.setImageResource(detailValue.getDrawable());
            tvDetailValue.setText(detailValue.getValue());
            tvNameValue.setText(detailValue.getName());
        }
    }
}
