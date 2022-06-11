package edu.gcu.betterweather.ui;

import android.os.Bundle;
import android.view.View;

import edu.gcu.betterweather.R;
import edu.gcu.betterweather.databinding.ActivityAboutPageBinding;
import edu.gcu.betterweather.databinding.ActivityMainBinding;
import edu.gcu.betterweather.nav.BetterWeatherMainActivity;

public class AboutPage extends BetterWeatherMainActivity {

    private ActivityAboutPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        allocateActivityTitle("About Us");
    }
}