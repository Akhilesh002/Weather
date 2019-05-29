package com.akhilesh002.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhilesh002.weather.pojo.Weather;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Weather> weatherArrayList;

    public WeatherAdapter(Context cxt, ArrayList<Weather> weatherArrayList) {
        inflater = LayoutInflater.from(cxt);
        this.weatherArrayList = weatherArrayList;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.per_hour_temperature, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder viewHolder, int i) {
        viewHolder.hour.setText(weatherArrayList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return weatherArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hour;
        private ImageView weatherIcon;
        private TextView temp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hour = itemView.findViewById(R.id.hour);
            weatherIcon = itemView.findViewById(R.id.hour_temperature_image);
            temp = itemView.findViewById(R.id.hour_temperature);
        }
    }
}
