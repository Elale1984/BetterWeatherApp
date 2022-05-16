package edu.gcu.betterweather;

import android.widget.ImageView;

public class DayWeather {

    //Properties
    private String weekDay;
    private ImageView morningWeather;
    private ImageView eveningWeather;
    private String lowTemp;
    private String highTemp;
    private String uvIndex;
    private String windSpeed;
    private String humidity;



    // Constructor
    public DayWeather(String weekDay, ImageView morningWeather, ImageView eveningWeather,
                      String lowTemp, String highTemp, String uvIndex, String windSpeed,
                      String humidity) {
        this.weekDay = weekDay;
        this.morningWeather = morningWeather;
        this.eveningWeather = eveningWeather;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
        this.uvIndex = uvIndex;
        this.windSpeed = windSpeed;
        this.humidity = humidity;

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

    public String getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(String uvIndex) {
        this.uvIndex = uvIndex;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

}
