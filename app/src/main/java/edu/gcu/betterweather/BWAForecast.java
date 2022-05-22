package edu.gcu.betterweather;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BWAForecast {
    @SerializedName("days")
    @Expose
    private BWAData[] days = null;

    public BWAData[] getDays() {
        return days;
    }

    public void setDays(BWAData[] days) {
        this.days = days;
    }

    public BWAForecast(BWAData[] days) {
        this.days = days;
    }
}
