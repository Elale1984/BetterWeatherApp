package edu.gcu.betterweather;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import edu.gcu.betterweather.databinding.ActivityTenDayForcastBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenDayForecast extends BetterWeatherMainActivity {

    private static final String TAG = "TenDayForecast";

    private ArrayList<String> dates = new ArrayList<>(10);
    private ArrayList<String> highTemps= new ArrayList<>(10);
    private ArrayList<String> lowTemps= new ArrayList<>(10);
    private ArrayList<String> uvLevels= new ArrayList<>(10);
    private ArrayList<String> humidityPercents= new ArrayList<>(10);
    private ArrayList<String> windsSpeeds= new ArrayList<>(10);
    private ActivityTenDayForcastBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTenDayForcastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getForecast();
        buildRecyclerView();
    }


    private void getForecast() {
        Call<BWAForecast> call = RetrofitClient.getInstance().getMyApi().getForecast("9W8PBMYZLZRULGY57Q6BBLHN7");
        call.enqueue(new Callback<BWAForecast>() {
            @Override
            public void onResponse(Call<BWAForecast> call, @NonNull Response<BWAForecast> response) {
                BWAForecast myForecast = response.body();

                Log.d("myTag", response.toString());
                assert myForecast != null;

                setTenDayForecastData(myForecast);
            }

            @Override
            public void onFailure(Call<BWAForecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }

        });
    }

    private void setTenDayForecastData(BWAForecast forecast) {

        for (int i = 0; i <10; i++)
        {
            if(i > 0){
                dates.add(forecast.getDays()[i].getCurrDay());
                highTemps.add(forecast.getDays()[i].getHighTemp().toString() + "°");
                lowTemps.add(forecast.getDays()[i].getLowTemp().toString() + "°");
                humidityPercents.add(forecast.getDays()[i].getCurrHumidity().toString());
                windsSpeeds.add(forecast.getDays()[i].getCurrWindSpeed().toString());
                uvLevels.add(forecast.getDays()[i].getCurrUVIndexLevel().toString());
            }
          }
    }

    private void buildRecyclerView() {
        Log.d(TAG, "buildRecyclerView");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.dayWeatherOne.rvTenDayWeather.setLayoutManager(layoutManager);

        TenDayRecyclerViewAdapter adapter = new TenDayRecyclerViewAdapter(this, dates, highTemps, lowTemps, humidityPercents, windsSpeeds, uvLevels);
        binding.dayWeatherOne.rvTenDayWeather.setAdapter(adapter);
    }


}