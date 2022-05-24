package edu.gcu.betterweather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    @GET("London%2CUK")
    Call<BWAForecast> getForecast(@Query("key") String apiKey);
}
