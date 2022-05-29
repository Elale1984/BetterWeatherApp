package edu.gcu.betterweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/";
    @GET("services/timeline/{location}")
    Call<BWAForecast> getForecast(@Path("location") String location, @Query("key") String apiKey);
    @GET("services/timeline/{location}")
    Call<BWAForecast> getForecast( @Query("key") String apiKey);

}
