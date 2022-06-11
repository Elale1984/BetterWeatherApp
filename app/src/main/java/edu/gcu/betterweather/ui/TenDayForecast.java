package edu.gcu.betterweather.ui;

import static edu.gcu.betterweather.ui.MainUI.humidityPercents;
import static edu.gcu.betterweather.ui.MainUI.sunrises;
import static edu.gcu.betterweather.ui.MainUI.sunsets;
import static edu.gcu.betterweather.ui.MainUI.uvLevels;
import static edu.gcu.betterweather.ui.MainUI.windsSpeeds;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import edu.gcu.betterweather.adapters.TenDayRecyclerViewAdapter;
import edu.gcu.betterweather.databinding.ActivityTenDayForcastBinding;
import edu.gcu.betterweather.nav.BetterWeatherMainActivity;

public class TenDayForecast extends BetterWeatherMainActivity implements TenDayRecyclerViewAdapter.OnWeatherClickListener {

    private ActivityTenDayForcastBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTenDayForcastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // This will set the title in the toolbar
        allocateActivityTitle("10 Day Forecast");

        buildRecyclerView();
    }


    private void buildRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        binding.dayWeatherOne.rvTenDayWeather.setLayoutManager(layoutManager);

        TenDayRecyclerViewAdapter adapter = new TenDayRecyclerViewAdapter(
                MainUI.dates, MainUI.highTemps, MainUI.lowTemps,
                this);
        binding.dayWeatherOne.rvTenDayWeather.setAdapter(adapter);
    }


    @Override
    public void onDayClickListener(int position) {
        binding.dayWeatherOne.txtHumidity.setText(humidityPercents.get(position));
        binding.dayWeatherOne.txtUV.setText(uvLevels.get(position));
        binding.dayWeatherOne.txtWind.setText(windsSpeeds.get(position));
        binding.dayWeatherOne.txtSunriseTD.setText(sunrises.get(position));
        binding.dayWeatherOne.txtSunsetTD.setText(sunsets.get(position));
    }
}