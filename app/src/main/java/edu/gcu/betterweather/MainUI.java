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

}