package edu.gcu.betterweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


import com.google.firebase.auth.FirebaseAuth;

import edu.gcu.betterweather.databinding.ActivityMainBinding;


public class MainUI extends AppCompatActivity {
    private ActivityMainBinding binding;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.btnSignOut.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainUI.this, BWALoginView.class));
            finish();
        });
        // display current weather
        WeatherForecast forecast = new WeatherForecast("Hollywood,California");
        try {
            forecast.requestForecast(forecast.Location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        displayCurrentWeather(forecast);
        displayTenDay(forecast);

    }

    private void displayCurrentWeather(WeatherForecast forecast)
    {
        binding.txtCurrentCity.setText(forecast.Location);
        binding.txtCurrentTemp.setText(forecast.forecast.get(0).getCurrTemp());
        binding.txtUVIndex.setText(forecast.forecast.get(0).getCurrUVIndexLevel());
        binding.txtWindSpeed.setText(forecast.forecast.get(0).getCurrWindSpeed());
        binding.txtHumidityPercent.setText(forecast.forecast.get(0).getCurrHumidity());

    }

    private void displayTenDay(WeatherForecast forecast)
    {
        for (int x = 0; x <forecast.forecast.size(); x++)
        {
            String date = forecast.forecast.get(x).getCurrDay();
            String maxtemp = forecast.forecast.get(x).getHighTemp();
            String mintemp = forecast.forecast.get(x).getLowTemp();
            switch(x){
                case 0:
                    binding.dayWeatherOne.txtWeekDayOne.setText(date);
                    binding.dayWeatherOne.txtTempHighOne.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowOne.setText(mintemp);
                    break;
                case 1:
                    binding.dayWeatherOne.txtWeekDayTwo.setText(date);
                    binding.dayWeatherOne.txtTempHighTwo.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowTwo.setText(mintemp);
                    break;
                case 2:
                    binding.dayWeatherOne.txtWeekDayThree.setText(date);
                    binding.dayWeatherOne.txtTempHighThree.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowThree.setText(mintemp);
                    break;
                case 3:
                    binding.dayWeatherOne.txtWeekDayFour.setText(date);
                    binding.dayWeatherOne.txtTempHighFour.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowFour.setText(mintemp);
                    break;
                case 4:
                    binding.dayWeatherOne.txtWeekDayFive.setText(date);
                    binding.dayWeatherOne.txtTempHighFive.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowFive.setText(mintemp);
                    break;
                case 5:
                    binding.dayWeatherOne.txtWeekDaySix.setText(date);
                    binding.dayWeatherOne.txtTempHighSix.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowSix.setText(mintemp);
                    break;
                case 6:
                    binding.dayWeatherOne.txtWeekDaySeven.setText(date);
                    binding.dayWeatherOne.txtTempHighSeven.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowSeven.setText(mintemp);
                    break;
                case 7:
                    binding.dayWeatherOne.txtWeekDayEight.setText(date);
                    binding.dayWeatherOne.txtTempHighEight.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowEight.setText(mintemp);
                    break;
                case 8:
                    binding.dayWeatherOne.txtWeekDayNine.setText(date);
                    binding.dayWeatherOne.txtTempHighNine.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowNine.setText(mintemp);
                    break;
                case 9:
                    binding.dayWeatherOne.txtWeekDayTen.setText(date);
                    binding.dayWeatherOne.txtTempHighTen.setText(maxtemp);
                    binding.dayWeatherOne.txtTempLowTen.setText(mintemp);
                    break;
            }
        }

    }
}
