package edu.gcu.betterweather;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;

import edu.gcu.betterweather.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainUI extends BetterWeatherMainActivity {
    private ActivityMainBinding binding;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // This will set the title in the toolbar
        allocateActivityTitle("Current Weather");

        // This sets the mAuth to the instance of firebase
        mAuth = FirebaseAuth.getInstance();

        binding.btnSignOut.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainUI.this, BWALoginView.class));
            finish();
        });

        // display current weather
        getForecast();

    }


    private void getForecast() {
        Call<BWAForecast> call = RetrofitClient.getInstance().getMyApi().getForecast("9W8PBMYZLZRULGY57Q6BBLHN7");
        call.enqueue(new Callback<BWAForecast>() {
            @Override
            public void onResponse(Call<BWAForecast> call, @NonNull Response<BWAForecast> response) {
                BWAForecast myForecast = response.body();

                Log.d("myTag", response.toString());
                assert myForecast != null;
                displayCurrentWeather(myForecast);
                displayTenDay(myForecast);
            }

            @Override
            public void onFailure(Call<BWAForecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }

        });
    }

    private void displayCurrentWeather(BWAForecast forecast)
    {
        binding.txtCurrentCity.setText("Overland Park, Kansas");
        binding.txtCurrentTemp.setText(forecast.getDays()[0].getCurrTemp().toString()+"°");
        binding.txtUVIndex.setText(forecast.getDays()[0].getCurrUVIndexLevel().toString());
        binding.txtWindSpeed.setText(forecast.getDays()[0].getCurrWindSpeed().toString());
        binding.txtHumidityPercent.setText(forecast.getDays()[0].getCurrHumidity().toString());
    }

    private void displayTenDay(BWAForecast forecast)
    {
        for (int i = 0; i <10; i++)
        {
            String date = forecast.getDays()[i].getCurrDay();
            String maxtemp = forecast.getDays()[i].getHighTemp().toString();
            String mintemp = forecast.getDays()[i].getLowTemp().toString();
            switch(i){
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
            Log.d("myTag", "success");
        }
    }
}