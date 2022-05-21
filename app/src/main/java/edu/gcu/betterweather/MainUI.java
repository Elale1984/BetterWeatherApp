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
        WeatherForecast forecast = new WeatherForecast("Hollywood, California");
        displayCurrentWeather(forecast);
        displayTenDay(forecast);

    }

    private void displayCurrentWeather(WeatherForecast forecast)
    {
        TextView city = (TextView)findViewById(R.id.txtCurrentCity);
        city.setText(forecast.Location);

        TextView temp = (TextView)findViewById(R.id.txtCurrentTemp);
        temp.setText(forecast.forecast.get(0).getCurrTemp());

        TextView UVIndex = (TextView)findViewById(R.id.txtUVIndex);
        UVIndex.setText(forecast.forecast.get(0).getCurrUVIndexLevel());

        TextView wind = (TextView)findViewById(R.id.txtWindSpeed);
        UVIndex.setText(forecast.forecast.get(0).getCurrWindSpeed());

        TextView humidity = (TextView)findViewById(R.id.txtHumidityPercent);
        UVIndex.setText(forecast.forecast.get(0).getCurrHumidity());
    }

    private void displayTenDay(WeatherForecast forecast)
    {
        TextView date = (TextView)findViewById(R.id.txtWeekDayOne);
        TextView maxtemp = (TextView)findViewById(R.id.txtTempHighOne);
        TextView mintemp = (TextView)findViewById(R.id.txtTempLowOne);
        // Day one weather
        for (int x = 0; x <forecast.forecast.size(); x++)
        {
            switch(x){
                case 0:
                    date = (TextView)findViewById(R.id.txtWeekDayOne);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighOne);
                    mintemp = (TextView)findViewById(R.id.txtTempLowOne);
                    break;
                case 1:
                    date = (TextView)findViewById(R.id.txtWeekDayTwo);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighTwo);
                    mintemp = (TextView)findViewById(R.id.txtTempLowTwo);
                    break;
                case 2:
                    date = (TextView)findViewById(R.id.txtWeekDayThree);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighThree);
                    mintemp = (TextView)findViewById(R.id.txtTempLowThree);
                    break;
                case 3:
                    date = (TextView)findViewById(R.id.txtWeekDayFour);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighFour);
                    mintemp = (TextView)findViewById(R.id.txtTempLowFour);
                    break;
                case 4:
                    date = (TextView)findViewById(R.id.txtWeekDayFive);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighFive);
                    mintemp = (TextView)findViewById(R.id.txtTempLowFive);
                    break;
                case 5:
                    date = (TextView)findViewById(R.id.txtWeekDaySix);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighSix);
                    mintemp = (TextView)findViewById(R.id.txtTempLowSix);
                    break;
                case 6:
                    date = (TextView)findViewById(R.id.txtWeekDaySeven);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighSeven);
                    mintemp = (TextView)findViewById(R.id.txtTempLowSeven);
                    break;
                case 7:
                    date = (TextView)findViewById(R.id.txtWeekDayEight);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighEight);
                    mintemp = (TextView)findViewById(R.id.txtTempLowEight);
                    break;
                case 8:
                    date = (TextView)findViewById(R.id.txtWeekDayNine);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighNine);
                    mintemp = (TextView)findViewById(R.id.txtTempLowNine);
                    break;
                case 9:
                    date = (TextView)findViewById(R.id.txtWeekDayTen);
                    maxtemp = (TextView)findViewById(R.id.txtTempHighTen);
                    mintemp = (TextView)findViewById(R.id.txtTempLowTen);
                    break;
            }
            date.setText(forecast.forecast.get(x).getCurrDay());
            maxtemp.setText(forecast.forecast.get(x).getHighTemp());
            mintemp.setText(forecast.forecast.get(x).getLowTemp());
        }

    }
}
