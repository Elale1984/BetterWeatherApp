package edu.gcu.betterweather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import edu.gcu.betterweather.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainUI extends AppCompatActivity {
    private ActivityMainBinding binding;

    private FirebaseAuth mAuth;

    private String location = "90721";

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

        binding.txtCurrentCity.setOnClickListener(v -> {
            MaterialAlertDialogBuilder textInput = new MaterialAlertDialogBuilder(MainUI.this);
            textInput.setTitle("Change Location");
            textInput.setMessage("Please input the name or postal code of the location here:");
            final EditText input = new EditText(textInput.getContext());
            textInput.setView(input);
            textInput.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    location = input.getText().toString();
                    getForecast(location);
                }
            });
            textInput.show();
        });
        // display current weather
        getForecast(location);

    }

    private void getForecast(String address) {
        Call<BWAForecast> call = RetrofitClient.getInstance().getMyApi().getForecast( address,"9W8PBMYZLZRULGY57Q6BBLHN7");
        call.enqueue(new Callback<BWAForecast>() {
            @Override
            public void onResponse(Call<BWAForecast> call, Response<BWAForecast> response) {
                BWAForecast myForecast = response.body();

                Log.d("myTag", response.toString());
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
        String[] fullAddress = forecast.getName().split(",");

        String address = fullAddress[0] + ", " + fullAddress[1];

        binding.txtCurrentCity.setText(address);
        binding.txtCurrentTemp.setText(forecast.getDays()[0].getCurrTemp().toString());
        binding.txtUVIndex.setText(forecast.getDays()[0].getCurrUVIndexLevel().toString());
        binding.txtWindSpeed.setText(forecast.getDays()[0].getCurrWindSpeed().toString());
        binding.txtHumidityPercent.setText(forecast.getDays()[0].getCurrHumidity().toString());
    }

    private void displayTenDay(BWAForecast forecast)
    {
        for (Integer i = 0; i <10; i++)
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
