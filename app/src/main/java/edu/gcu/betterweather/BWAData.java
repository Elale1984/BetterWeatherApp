package edu.gcu.betterweather;

import com.google.gson.annotations.SerializedName;

public class BWAData {
    @SerializedName("datetime")
    private String currDay;

    @SerializedName("temp")
    private Double currTemp;

    @SerializedName("windspeed")
    private Double currWindSpeed;

    @SerializedName("uvindex")
    private Double currUVIndexLevel;

    @SerializedName("humidity")
    private Double currHumidity;

    @SerializedName("tempmax")
    private Double highTemp;

    @SerializedName("tempmin")
    private Double lowTemp;



    public BWAData(String currDay, Double currTemp, Double currWindSpeed, Double currUVIndexLevel, Double currHumidity, Double highTemp, Double lowTemp) {
        this.currDay = currDay;
        this.currTemp = currTemp;
        this.currWindSpeed = currWindSpeed;
        this.currUVIndexLevel = currUVIndexLevel;
        this.currHumidity = currHumidity;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }


    public String getCurrDay() {
        return currDay;
    }

    public void setCurrDay(String currDay) {
        this.currDay = currDay;
    }

    public Double getCurrTemp() {
        return currTemp;
    }

    public void setCurrTemp(Double currTemp) {
        this.currTemp = currTemp;
    }

    public Double getCurrWindSpeed() {
        return currWindSpeed;
    }

    public void setCurrWindSpeed(Double currWindSpeed) {
        this.currWindSpeed = currWindSpeed;
    }

    public Double getCurrUVIndexLevel() {
        return currUVIndexLevel;
    }

    public void setCurrUVIndexLevel(Double currUVIndexLevel) {
        this.currUVIndexLevel = currUVIndexLevel;
    }

    public Double getCurrHumidity() { return currHumidity;}

    public void setCurrHumidity(Double currHumidity) { this.currHumidity = currHumidity; }

    public Double getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(Double highTemp) {
        this.highTemp = highTemp;
    }

    public Double getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(Double lowTemp) {
        this.lowTemp = lowTemp;
    }
}
