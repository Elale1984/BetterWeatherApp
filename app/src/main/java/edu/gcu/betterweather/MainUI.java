package edu.gcu.betterweather;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private static final String KEY_CITY_ZIPCODE = "zipcode";
    public static String location = "90721";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference currentCityReference = db.document("Cities/MyCities");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        setContentView(view);
        loadLocation();
        Map<String, Object> myLocations = new HashMap<>();

        // This will set the title in the toolbar
        allocateActivityTitle("Current Weather");

        // This sets the mAuth to the instance of firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
                    myLocations.put(KEY_CITY_ZIPCODE, location);
                    db.collection("Cities").document("MyCities").set(myLocations)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(MainUI.this, "City Saved", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainUI.this, "Error!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                    getForecast(location);
                }
            });
            textInput.show();
        });

        // display current weather
        getForecast(location);


    }

    private void loadLocation() {

        currentCityReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    location = documentSnapshot.getString(KEY_CITY_ZIPCODE);
                }
                else{
                    Toast.makeText(MainUI.this, "Document Does not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainUI.this, "Error retrieving data!", Toast.LENGTH_SHORT).show();
            }
        });

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