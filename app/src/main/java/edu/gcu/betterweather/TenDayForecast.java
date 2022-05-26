package edu.gcu.betterweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import edu.gcu.betterweather.databinding.ActivityMainBinding;
import edu.gcu.betterweather.databinding.ActivityTenDayForcastBinding;

public class TenDayForecast extends AppCompatActivity {

    private static final String TAG = "TenDayForecast";
    private ArrayList<BWAData> tenDayForecast;
    private ActivityTenDayForcastBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTenDayForcastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }

    private void buildRecyclerView() {
        Log.d( TAG, "buildRecyclerView");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.dayWeatherOne.rvTenDayWeather.setLayoutManager(layoutManager);
        TenDayRecyclerViewAdapter adapter = new TenDayRecyclerViewAdapter(this, tenDayForecast);
        binding.dayWeatherOne.rvTenDayWeather.setAdapter(adapter);
    }
}