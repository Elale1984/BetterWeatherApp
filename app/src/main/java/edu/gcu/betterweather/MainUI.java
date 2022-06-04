package edu.gcu.betterweather;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import edu.gcu.betterweather.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainUI extends BetterWeatherMainActivity {

    public static ArrayList<String> dates = new ArrayList<>();
    public static ArrayList<String> highTemps= new ArrayList<>();
    public static ArrayList<String> lowTemps= new ArrayList<>();
    public static ArrayList<String> uvLevels= new ArrayList<>();
    public static ArrayList<String> humidityPercents= new ArrayList<>();
    public static ArrayList<String> windsSpeeds= new ArrayList<>();

    private ActivityMainBinding binding;

    private static final String KEY_CURRENT_LOCATION = "current location";



    public static String location;
    private static  FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static String uID;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("current_city");

    CacheDatabase cacheDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // This will set the title in the toolbar
        allocateActivityTitle("Current Weather");

        // Access the local cache to get user data
        UserData userData;

        userData = new UserData()
        CacheDatabase cacheDatabase = new CacheDatabase(MainUI.this);


        // button click to change the current location
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
                    myRef.child("users").child(uID).child("current_city").setValue(location);
                    getForecast(location);
                }
            });
            textInput.show();
        });

        getForecast(location);


    }



    @Override
    protected void onStart() {
        super.onStart();


    }



    public void getForecast(String address) {
        Call<BWAForecast> call = RetrofitClient.getInstance().getMyApi().getForecast( address,"9W8PBMYZLZRULGY57Q6BBLHN7");
        call.enqueue(new Callback<BWAForecast>() {
            @Override
            public void onResponse(Call<BWAForecast> call, Response<BWAForecast> response) {
                BWAForecast myForecast = response.body();
                resetData();

                Log.d("myTag", response.toString());
                for (int i = 0; i <10; i++)
                {


                    dates.add(myForecast.getDays()[i].getCurrDay());
                    highTemps.add(myForecast.getDays()[i].getHighTemp().toString() + "°");
                    lowTemps.add(myForecast.getDays()[i].getLowTemp().toString() + "°");
                    humidityPercents.add(myForecast.getDays()[i].getCurrHumidity().toString());
                    windsSpeeds.add(myForecast.getDays()[i].getCurrWindSpeed().toString());
                    uvLevels.add(myForecast.getDays()[i].getCurrUVIndexLevel().toString());

                }
                displayCurrentWeather(myForecast);

            }

            @Override
            public void onFailure(Call<BWAForecast> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }

        });
    }

    private void resetData() {
        dates.clear();
        highTemps.clear();
        lowTemps.clear();
        humidityPercents.clear();
        windsSpeeds.clear();
        uvLevels.clear();
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

}