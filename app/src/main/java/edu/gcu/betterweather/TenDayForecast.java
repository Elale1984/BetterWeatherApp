package edu.gcu.betterweather;

import static edu.gcu.betterweather.MainUI.humidityPercents;
import static edu.gcu.betterweather.MainUI.location;
import static edu.gcu.betterweather.MainUI.uvLevels;
import static edu.gcu.betterweather.MainUI.windsSpeeds;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import edu.gcu.betterweather.databinding.ActivityTenDayForcastBinding;
public class TenDayForecast extends BetterWeatherMainActivity implements TenDayRecyclerViewAdapter.OnWeatherClickListener{

    private static final String TAG = "TenDayForecast";


    private ActivityTenDayForcastBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTenDayForcastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // This will set the title in the toolbar
        allocateActivityTitle("10 Day Forecast");

        // display current weather
        getForecast(location);

        buildRecyclerView();
    }

    private void getForecast(String location) {
    }


    private void buildRecyclerView() {
        Log.d(TAG, "buildRecyclerView");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.dayWeatherOne.rvTenDayWeather.setLayoutManager(layoutManager);

        TenDayRecyclerViewAdapter adapter = new TenDayRecyclerViewAdapter(this, MainUI.dates, MainUI.highTemps, MainUI.lowTemps, humidityPercents, MainUI.windsSpeeds, MainUI.uvLevels, this);
        binding.dayWeatherOne.rvTenDayWeather.setAdapter(adapter);
    }


    @Override
    public void onDayClickListener(int position) {
        binding.dayWeatherOne.txtHumidity.setText(humidityPercents.get(position));
        binding.dayWeatherOne.txtUV.setText(uvLevels.get(position));
        binding.dayWeatherOne.txtWind.setText(windsSpeeds.get(position));


    }
}