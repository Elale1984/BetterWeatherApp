package edu.gcu.betterweather.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import edu.gcu.betterweather.R;
import edu.gcu.betterweather.data.api.RetrofitClient;
import edu.gcu.betterweather.data.model.BWAForecast;
import edu.gcu.betterweather.databinding.ActivityMainBinding;
import edu.gcu.betterweather.nav.BetterWeatherMainActivity;
import edu.gcu.betterweather.utils.UserHelperClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainUI extends BetterWeatherMainActivity
        implements View.OnLongClickListener, View.OnClickListener {

    public static ArrayList<String> dates = new ArrayList<>();
    public static ArrayList<String> highTemps= new ArrayList<>();
    public static ArrayList<String> lowTemps= new ArrayList<>();
    public static ArrayList<String> uvLevels= new ArrayList<>();
    public static ArrayList<String> humidityPercents= new ArrayList<>();
    public static ArrayList<String> windsSpeeds= new ArrayList<>();
    public static ArrayList<String> sunrises = new ArrayList<>();
    public static ArrayList<String> sunsets = new ArrayList<>();

    private ActivityMainBinding binding;
    public static String location;
    public Dialog myCitiesDialog;
    public TextView currentCity, secondCity, thirdCity;

    public static String units = "us";

    // Firebase variables
    private static final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final String uID = mAuth.getUid();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");

    public static UserHelperClass user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // This will set the title in the toolbar
        allocateActivityTitle("Current Weather");




        // button click to change the current location
        binding.txtCurrentCity.setOnClickListener(v -> openMyCitiesDialog());

        // Listener for the f/c switch
        binding.switchUnits.setOnCheckedChangeListener((v, s) -> {
            if ( s /*binding.switchUnits.isChecked()*/)
                units = "metric";
            else
                units = "us";
            getForecast(location, units);
        });

        // display current weather
        getForecast(location, units);


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

    private void openMyCitiesDialog() {

        myCitiesDialog = new Dialog(this, R.style.DialogStyle);
        myCitiesDialog.setContentView(R.layout.change_location_dialog);
        myCitiesDialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
        currentCity = myCitiesDialog.findViewById(R.id.tvItemCurrentCity);
        secondCity = myCitiesDialog.findViewById(R.id.tvItemCityTwo);
        thirdCity = myCitiesDialog.findViewById(R.id.tvItemCityThree);
        FloatingActionButton addCity = myCitiesDialog.findViewById(R.id.fabAddCity);





        currentCity.setText(user.getCurrentCity());

        if(user.getSecondCity() != null) {

            secondCity.setText(user.getSecondCity());
            secondCity.setVisibility(View.VISIBLE);
        }

        if(user.getThirdCity() != null){
            thirdCity.setText(user.getThirdCity());
            thirdCity.setVisibility(View.VISIBLE);
        }


        // sets the FAB visibility to INVISIBLE if there are 3 cities already added
        if(thirdCity.getVisibility() == View.VISIBLE){
            addCity.setVisibility(View.INVISIBLE);
        }

        // set onClickListener to select a different city
        currentCity.setOnClickListener(this);
        secondCity.setOnClickListener(this);
        thirdCity.setOnClickListener(this);

        // set onLongClickListener to update a favorite cities to new city
        currentCity.setOnLongClickListener(this);
        secondCity.setOnLongClickListener(this);
        thirdCity.setOnLongClickListener(this);


        addCity.setOnClickListener(view -> {
            if(secondCity.getVisibility() == View.INVISIBLE) {
                setCity(secondCity);
            }
            else {
                setCity(thirdCity);
                addCity.setVisibility(View.INVISIBLE);
            }

        });
        myCitiesDialog.show();

    }

    private void setCity(TextView cityView) {
        MaterialAlertDialogBuilder textInput = new MaterialAlertDialogBuilder(MainUI.this);
        textInput.setTitle("Change Location");
        textInput.setMessage("Please input the name or postal code of the location here:");
        final EditText input = new EditText(textInput.getContext());
        textInput.setView(input);
        textInput.setPositiveButton("OK", (dialog, which) -> {


            String cityZipCode = input.getText().toString();

            // Update FireStore realtime data with new cities
            if(cityView.getId() == R.id.tvItemCurrentCity){
                location = cityZipCode;
                updateCurrentCityToFirebaseRealtimeDatabase();
                cityZipCode = location;
            }
            if(cityView.getId() == R.id.tvItemCityTwo){
                user.setSecondCity(cityZipCode);
                updateSecondCityToFirebaseRealtimeDatabase(user.getSecondCity());
            }
            if(cityView.getId() == R.id.tvItemCityThree){
                user.setThirdCity(input.getText().toString());
                updateThirdCityToFirebaseRealtimeDatabase(user.getThirdCity());
            }

            // updates the UI with the new forecast information
            cityView.setText(cityZipCode);
            cityView.setVisibility(View.VISIBLE);


        });
        textInput.show();
    }

    private void updateThirdCityToFirebaseRealtimeDatabase(String thirdCity) {
        myRef.child(uID).child("thirdCity").setValue(thirdCity);
        myRef.keepSynced(true);
    }

    private void updateSecondCityToFirebaseRealtimeDatabase(String secondCity) {
        // update Realtime Database with the new city

        myRef.child(uID).child("secondCity").setValue(secondCity);
        myRef.keepSynced(true);
    }

    private void updateCurrentCityToFirebaseRealtimeDatabase() {
        // update Realtime Database with the new city
        if(location == null || location.equals(""))
            location = "66212";
        myRef.child(uID).child("city").setValue(location);

        myRef.keepSynced(true);


    }

    /*
     *
     * This method gets the data from the snapshot and updates the UI with the appropriate
     * data, then retrieves the forecast.
     */
    private void updateUI(DataSnapshot snapshot) {

        user = new UserHelperClass();
        user.setName(Objects.requireNonNull(snapshot.child(uID).getValue(UserHelperClass.class)).getName());
        user.setEmail(Objects.requireNonNull(snapshot.child(uID).getValue(UserHelperClass.class)).getEmail());
        user.setCurrentCity(Objects.requireNonNull(snapshot.child(uID).getValue(UserHelperClass.class)).getCurrentCity());
        user.setSecondCity(Objects.requireNonNull(snapshot.child(uID).getValue(UserHelperClass.class)).getSecondCity());
        user.setThirdCity(Objects.requireNonNull(snapshot.child(uID).getValue(UserHelperClass.class)).getThirdCity());

        location = user.currentCity;

        getForecast(location, units);
    }


    @Override
    protected void onStart() {
        super.onStart();


    }



    public void getForecast(String address, String units) {
        Call<BWAForecast> call = RetrofitClient.getInstance().getMyApi().
                getForecast( address, units, "9W8PBMYZLZRULGY57Q6BBLHN7");
        call.enqueue(new Callback<BWAForecast>() {
            @Override
            public void onResponse(@NonNull Call<BWAForecast> call, @NonNull Response<BWAForecast> response) {
                BWAForecast myForecast = response.body();
                resetData();

                for (int i = 0; i <10; i++)
                {


                    assert myForecast != null;
                    dates.add(myForecast.getDays()[i].getCurrDay());
                    highTemps.add(myForecast.getDays()[i].getHighTemp().toString() + "??");
                    lowTemps.add(myForecast.getDays()[i].getLowTemp().toString() + "??");
                    humidityPercents.add(myForecast.getDays()[i].getCurrHumidity().toString());
                    windsSpeeds.add(myForecast.getDays()[i].getCurrWindSpeed().toString());
                    uvLevels.add(myForecast.getDays()[i].getCurrUVIndexLevel().toString());

                    sunrises.add(myForecast.getDays()[i].getSunrise());
                    sunsets.add(myForecast.getDays()[i].getSunset());

                }
                displayCurrentWeather(myForecast);

            }

            @Override
            public void onFailure(@NonNull Call<BWAForecast> call, @NonNull Throwable t) {

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
        sunrises.clear();
        sunsets.clear();
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
        binding.txtSunrise.setText(forecast.getDays()[0].getSunrise());
        binding.txtSunset.setText(forecast.getDays()[0].getSunset());

        // Getting data from realtime database
        myRef.child(uID).child("current_city").get()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainUI.this, "Failed to get data from firebase",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }

    // this onLongClick event will handle the event for each city in favorites cities

    @Override
    public boolean onLongClick(View view) {
        if(view.getId() == R.id.tvItemCurrentCity){
            setCity(currentCity);
        }
        if (view.getId() == R.id.tvItemCityTwo) {
            setCity(secondCity);
        }
        if (view.getId() == R.id.tvItemCityThree) {
            setCity(thirdCity);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tvItemCurrentCity){
            location = currentCity.getText().toString();

        }
        if (view.getId() == R.id.tvItemCityTwo) {
            location = secondCity.getText().toString();

        }
        if (view.getId() == R.id.tvItemCityThree) {
            location = thirdCity.getText().toString();
        }
        getForecast(location, units);
        myCitiesDialog.dismiss();
    }
}