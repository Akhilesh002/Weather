package com.akhilesh002.weather;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.akhilesh002.weather.pojo.PerDayTemperature;
import com.akhilesh002.weather.pojo.Weather;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout parentConstraintLayout;
    private TextView cityName;
    private TextView cityTemperature;
    private TextView day;
    private TextView minimumTemperature;
    private TextView maximumTemperature;
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String API_ID = "868397701ee2f40465d7cf17969fef82";
    String url;

//    http://api.openweathermap.org/data/2.5/weather?q=201301&APPID=868397701ee2f40465d7cf17969fef82

    private RecyclerView recyclerView;
    private ArrayList<Weather> weatherArrayList;
    private WeatherAdapter weatherAdapter;
    private String[] hour = new String[]{"00","01" ,"02","03","04","05","06","07","08","09","10","11","12","13","14","15","16",
            "17","18","19","20","21","22","23"};

    private RecyclerView recyclerView2;
    private ArrayList<PerDayTemperature> perDayTemperatureArrayList;
    private PerDayTemperatureAdapter perDayTemperatureAdapter;
    private String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private TextView dayDescription;
    private TextView sunRise;
    private TextView sunSet;
    private TextView chanceOfRain;
    private TextView humidity;
    private TextView windSpeed;
    private TextView feelsLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentConstraintLayout = findViewById(R.id.parent_layout);
        cityName = findViewById(R.id.city_name);
        cityTemperature = findViewById(R.id.city_temperature);
        day = findViewById(R.id.day);
        minimumTemperature = findViewById(R.id.minimum_temperature);
        maximumTemperature = findViewById(R.id.maximum_temperature);


        recyclerView = findViewById(R.id.per_hour_temperature_list);
        weatherArrayList = populateWeather();
        weatherAdapter = new WeatherAdapter(this, weatherArrayList);
        recyclerView.setAdapter(weatherAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView2 = findViewById(R.id.per_day_temperature_list);
        perDayTemperatureArrayList = populateDays();
        perDayTemperatureAdapter = new PerDayTemperatureAdapter(this, perDayTemperatureArrayList);
        recyclerView2.setAdapter(perDayTemperatureAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        dayDescription = findViewById(R.id.day_description);
        sunRise = findViewById(R.id.sun_rise);
        sunSet = findViewById(R.id.sun_set);
        chanceOfRain = findViewById(R.id.chance_of_rain);
        humidity = findViewById(R.id.humidity);
        windSpeed = findViewById(R.id.wind_speed);
        feelsLike = findViewById(R.id.feels_like);

        url = BASE_URL + 201301 + "&APPID=" + API_ID;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "noida");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Weather weather;
                        Gson gson = new Gson();
                        weather = gson.fromJson(response.toString(), Weather.class);
                        JSONObject object = null;
                        try {
                            object = new JSONObject(String.valueOf(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject mainObject = null;
                        JSONObject sysObject = null;
                        JSONObject windObject = null;
                        try {
                            if (object != null) {
                                mainObject = object.getJSONObject("main");
                                sysObject = object.getJSONObject("sys");
                                windObject = object.getJSONObject("wind");
                            }else{
                                Toast.makeText(MainActivity.this, "Parent Array return null", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        cityName.setText(weather.getName());
                        try {
                            if (mainObject != null && sysObject != null && windObject != null) {
                                double currentTemperature;
                                currentTemperature = ((((Double.parseDouble(mainObject.getString("temp")) - 273.15) * 9) / 5) + 32);
                                cityTemperature.setText(new DecimalFormat("##.##").format(currentTemperature));
                                float ct = Float.parseFloat(new DecimalFormat("##.##").format(currentTemperature));

                                if (ct > 100){
                                    parentConstraintLayout.setBackgroundResource(R.drawable.background_2);
                                }else if(ct > 99 && ct <= 100){
                                    parentConstraintLayout.setBackgroundColor(0x99000099);
                                }else if (ct > 98 && ct <= 99){
                                    parentConstraintLayout.setBackgroundColor(0x88000088);
                                }else if(ct > 97 && ct <= 98){
                                    parentConstraintLayout.setBackgroundColor(0x77000077);
                                }else if(ct > 96 && ct <= 97){
                                    parentConstraintLayout.setBackgroundColor(0xAA000066);
                                }else if(ct > 95 && ct <= 96){
                                    parentConstraintLayout.setBackgroundColor(0x55000055);
                                }else if(ct > 94 && ct <= 95){
                                    parentConstraintLayout.setBackgroundColor(0x44000044);
                                }else{
                                    parentConstraintLayout.setBackgroundResource(R.drawable.background_1);
                                }
                                double tempMax = ((((Double.parseDouble(mainObject.getString("temp_max")) - 273.15) * 9) / 5) + 32);
                                maximumTemperature.setText(new DecimalFormat("##.##").format(tempMax));
                                double tempMin = ((((Double.parseDouble(mainObject.getString("temp_min")) - 273.15) * 9) / 5) + 32);
                                minimumTemperature.setText(new DecimalFormat("##.##").format(tempMin));
                                day.setText(String.format("%s", getDay(weather.getDt())));

                                String description = response.getJSONArray("weather").getJSONObject(0).getString("description");
                                dayDescription.setText("Today: "+ description + " conditions with a heat index of "+
                                        (new DecimalFormat("##.#").format(currentTemperature)) +". The high will be "+
                                        (new DecimalFormat("##.#").format(tempMax))+"." );
                                dayDescription.setTextColor(Color.WHITE);
                                dayDescription.setTextSize(20);

                                sunRise.setText(getHour(Long.parseLong(sysObject.getString("sunrise"))));
                                sunSet.setText(getHour(Long.parseLong(sysObject.getString("sunset"))));
                                chanceOfRain.setText("abc");
                                humidity.setText(mainObject.getString("humidity"));
                                windSpeed.setText(windObject.getString("speed"));
                                feelsLike.setText(new DecimalFormat("##.##").format(currentTemperature));
                            }else{
                                Toast.makeText(MainActivity.this, "Return null", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private ArrayList<Weather> populateWeather() {
        ArrayList<Weather> list = new ArrayList<>();

        for(int i = 0; i < 24; i++){
            Weather weather = new Weather();
            weather.setName(hour[i]);
            list.add(weather);
        }
        return list;
    }

    private ArrayList<PerDayTemperature> populateDays() {
        ArrayList<PerDayTemperature> list =  new ArrayList<>();

        for (int i = 0; i < 7; i++){
            PerDayTemperature perDayTemperature = new PerDayTemperature();
            perDayTemperature.setDay(days[i]);
            list.add(perDayTemperature);
        }
        return list;
    }

    private String getDay(long time) {
//        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();//get your local time zone.
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(time * 1000);
        Date date = new Date();
        try {
            date = sdf.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputDate = new SimpleDateFormat("EEEE");
        return outputDate.format(date);
    }

    private String getHour(long time){
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(tz);
        String localTime = sdf.format(new Date(time * 1000));
        return String.valueOf(localTime);
    }
}
