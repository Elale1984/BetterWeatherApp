package edu.gcu.betterweather;

import android.widget.ImageView;

public class DayWeather {

    //Properties
    private String weekDay;
    private ImageView morningWeather;
    private ImageView eveningWeather;
    private String lowTemp;
    private String highTemp;


    // Constructor
    public DayWeather(String weekDay, ImageView morningWeather, ImageView eveningWeather, String lowTemp, String highTemp) {
        this.weekDay = weekDay;
        this.morningWeather = morningWeather;
        this.eveningWeather = eveningWeather;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

    // Getters And Setters
    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public ImageView getMorningWeather() {
        return morningWeather;
    }

    public void setMorningWeather(ImageView morningWeather) {
        this.morningWeather = morningWeather;
    }

    public ImageView getEveningWeather() {
        return eveningWeather;
    }

    public void setEveningWeather(ImageView eveningWeather) {
        this.eveningWeather = eveningWeather;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }
}
