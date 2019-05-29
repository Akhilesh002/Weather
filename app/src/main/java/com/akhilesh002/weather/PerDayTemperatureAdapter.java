package com.akhilesh002.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhilesh002.weather.pojo.PerDayTemperature;
import com.akhilesh002.weather.pojo.Weather;

import java.util.ArrayList;


public class PerDayTemperatureAdapter extends RecyclerView.Adapter<PerDayTemperatureAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<PerDayTemperature> perDayTemperatureArrayList;

    public PerDayTemperatureAdapter(Context cxt, ArrayList<PerDayTemperature> perDayTemperatureArrayList) {
        inflater = LayoutInflater.from(cxt);
        this.perDayTemperatureArrayList = perDayTemperatureArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.per_day_temperature, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.days.setText(perDayTemperatureArrayList.get(i).getDay());
    }

    @Override
    public int getItemCount() {
        return perDayTemperatureArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView days;
        private ImageView daysIcon;
        private TextView daysMinTemp;
        private TextView daysMaxTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            days = itemView.findViewById(R.id.days);
            daysIcon = itemView.findViewById(R.id.days_icon);
            daysMaxTemp = itemView.findViewById(R.id.days_temp_max);
            daysMinTemp = itemView.findViewById(R.id.days_temp_min);
        }
    }
}
