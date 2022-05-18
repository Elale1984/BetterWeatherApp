package edu.gcu.betterweather;

public class WeatherData {

    private String dayOfWeek;
    private String tempMax;
    private String tempMin;
    private String sunriseTime;
    private String sunSetTime;
    private String uvIndexLevel;
    private String humidityPercent;
    private String windSpeed;


    public WeatherData(String dayOfWeek, String tempMax, String tempMin, String sunriseTime, String sunSetTime, String uvIndexLevel, String humidityPercent, String windSpeed) {
        this.dayOfWeek = dayOfWeek;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.sunriseTime = sunriseTime;
        this.sunSetTime = sunSetTime;
        this.uvIndexLevel = uvIndexLevel;
        this.humidityPercent = humidityPercent;
        this.windSpeed = windSpeed;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(String sunSetTime) {
        this.sunSetTime = sunSetTime;
    }

    public String getUvIndexLevel() {
        return uvIndexLevel;
    }

    public void setUvIndexLevel(String uvIndexLevel) {
        this.uvIndexLevel = uvIndexLevel;
    }

    public String getHumidityPercent() {
        return humidityPercent;
    }

    public void setHumidityPercent(String humidityPercent) {
        this.humidityPercent = humidityPercent;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
