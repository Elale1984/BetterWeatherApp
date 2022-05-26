package edu.gcu.betterweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import edu.gcu.betterweather.databinding.ActivityBetterWeatherMainBinding;

public class BetterWeatherMainActivity extends AppCompatActivity {

    private ActivityBetterWeatherMainBinding binding;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBetterWeatherMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                
                if(item.getItemId() == R.id.nav_curr_weather) {
                    // TODO: 5/25/2022  
                }
                else if (item.getItemId() == R.id.nav_ten_day) {
                    // TODO: 5/25/2022  
                }
                else if (item.getItemId() == R.id.nav_about) {
                    // TODO: 5/25/2022  
                }
                else {
                    // TODO: 5/25/2022  
                }
               
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}