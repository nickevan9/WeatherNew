package com.netviet.weathernew.widget.customwidget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netviet.weathernew.R;

import java.util.List;

public class AirDetailAdapter extends RecyclerView.Adapter<AirDetailAdapter.ViewHolder> {

    private List<AirValue> valueList;
    private LayoutInflater mInflater;
    private Context context;

    public AirDetailAdapter(Context context,List<AirValue> airValues){
        this.valueList = airValues;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_air, parent, false);
        return new AirDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(valueList.get(position));
    }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

    public void applyData(List<AirValue> airValues){
        this.valueList = airValues;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvValue = itemView.findViewById(R.id.tv_value);
        }

        public void bindItem(AirValue airValue){
            tvValue.setText(String.valueOf((int) Double.parseDouble(airValue.getValue())));
            tvName.setText(airValue.getName());
        }
    }
}
