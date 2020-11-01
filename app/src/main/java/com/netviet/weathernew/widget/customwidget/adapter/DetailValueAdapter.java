package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
