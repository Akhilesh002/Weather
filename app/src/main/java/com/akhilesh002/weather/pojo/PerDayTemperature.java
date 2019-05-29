package com.akhilesh002.weather.pojo;

public class PerDayTemperature {

    private String day;
    private String image;
    private double temp_min;
    private double temp_max;


    public PerDayTemperature() {
    }

    public PerDayTemperature(String day, String image, double temp_min, double temp_max) {
        this.day = day;
        this.image = image;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
