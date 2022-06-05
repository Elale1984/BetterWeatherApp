package edu.gcu.betterweather;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public static String location;

    // Firebase variables
    private static  FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final String uID = mAuth.getUid();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // This will set the title in the toolbar
        allocateActivityTitle("Current Weather");




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

                    // update Realtime Database with the new city
                    myRef.child(uID).child("city").setValue(location);

                    // updates the UI with the new forecast information
                    getForecast(location);
                }
            });
            textInput.show();
        });

        /*
         * This listener will be called when the city is changed and when the
         * onCreate method is called. It will call a method to get the data from the
         * snapshot of the current database for the specific user.
         *
         */
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateUI(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    /*
     *
     * This method gets the data from the snapshot and updates the UI with the appropriate
     * data, then retrieves the forecast.
     */
    private void updateUI(DataSnapshot snapshot) {

        UserHelperClass user = new UserHelperClass();
        user.setName(snapshot.child(uID).getValue(UserHelperClass.class).getName());
        user.setEmail(snapshot.child(uID).getValue(UserHelperClass.class).getEmail());
        user.setCity(snapshot.child(uID).getValue(UserHelperClass.class).getCity());

        location = user.city;
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
            public void onResponse(@NonNull Call<BWAForecast> call,
                                   @NonNull Response<BWAForecast> response) {
                BWAForecast myForecast = response.body();
                resetData();

                Log.d("myTag", response.toString());
                for (int i = 0; i <10; i++)
                {


                    assert myForecast != null;
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
            public void onFailure(@NonNull Call<BWAForecast> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "There was an error getting " +
                        "the forecast from the weather API", Toast.LENGTH_LONG).show();
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

        // Getting data from realtime database
        myRef.child(uID).child("current_city").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainUI.this, "Failed to get data from firebase", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainUI.this, String.valueOf(task.getResult().getValue()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}