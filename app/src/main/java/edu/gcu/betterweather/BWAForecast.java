package edu.gcu.betterweather;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BWAForecast {
    @SerializedName("days")
    @Expose
    private BWAData[] days = null;

    @SerializedName("resolvedAddress")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BWAData[] getDays() {
        return days;
    }

    public void setDays(BWAData[] days) {
        this.days = days;
    }

    public BWAForecast(BWAData[] days, String name) {
        this.days = days;
        this.name = name;
    }
}