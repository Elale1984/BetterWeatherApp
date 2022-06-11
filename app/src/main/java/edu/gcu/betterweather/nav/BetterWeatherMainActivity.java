package edu.gcu.betterweather.nav;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import edu.gcu.betterweather.R;
import edu.gcu.betterweather.ui.AboutPage;
import edu.gcu.betterweather.ui.BWALoginView;
import edu.gcu.betterweather.ui.MainUI;
import edu.gcu.betterweather.ui.TenDayForecast;
import edu.gcu.betterweather.utils.UserHelperClass;


public class BetterWeatherMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;


    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_better_weather_main,  drawerLayout);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);




        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if(item.getItemId() == R.id.nav_curr_weather){
            startActivity(new Intent(this, MainUI.class));
            overridePendingTransition(0,0);
        }
        if(item.getItemId() == R.id.nav_ten_day) {
            startActivity(new Intent(this, TenDayForecast.class));
            overridePendingTransition(0,0);
        }
        if(item.getItemId() == R.id.nav_about) {
            startActivity(new Intent(this, AboutPage.class));
            overridePendingTransition(0,0);
        }
        if(item.getItemId() == R.id.nav_sign_out) {
            mAuth.signOut();
            startActivity(new Intent(this, BWALoginView.class));
        }

       return false;
    }

    protected void allocateActivityTitle(String currentActivityTitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(currentActivityTitle);
        }
    }
}