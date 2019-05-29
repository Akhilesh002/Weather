package com.akhilesh002.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhilesh002.weather.pojo.Weather;
import com.akhilesh002.weather.utility.AnimationUtil;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Weather> weatherArrayList;

    int previousPosition = -1;

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

        if (i > previousPosition){
            AnimationUtil.animate(viewHolder, true);
        }else{
            AnimationUtil.animate(viewHolder, false);
        }
        previousPosition = i;

        ScaleAnimation fade_in = new ScaleAnimation(0.25f, 1f, 0.25f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        fade_in.setDuration(800);
        fade_in.setFillAfter(true);
        viewHolder.weatherIcon.startAnimation(fade_in);
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
