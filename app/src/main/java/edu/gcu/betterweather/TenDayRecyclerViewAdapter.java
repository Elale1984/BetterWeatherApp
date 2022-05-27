package edu.gcu.betterweather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TenDayRecyclerViewAdapter extends RecyclerView.Adapter {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<BWAData> tenDayForecast;
    private ArrayList<String> dates;
    private ArrayList<String> highTemps;
    private ArrayList<String> lowTemps;
    private ArrayList<String> humidityPercents;
    private ArrayList<String> windsSpeeds;
    private ArrayList<String> uvLevels;

    private Context context;

    public TenDayRecyclerViewAdapter
            (Context context,
             ArrayList<String> dates,
             ArrayList<String> highTemps,
             ArrayList<String> lowTemps,
             ArrayList<String> humidityPercents,
             ArrayList<String> windsSpeeds,
             ArrayList<String> uvLevels)
    {

        this.context = context;
        this.dates = dates;
        this.highTemps = highTemps;
        this.lowTemps = lowTemps;
        this.humidityPercents = humidityPercents;
        this.windsSpeeds = windsSpeeds;
        this.uvLevels = uvLevels;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: called");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_day_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");


    }

    @Override
    public int getItemCount() {
        return tenDayForecast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayOfWeek, date, highTemp, lowTemp,
                humidityPercent, uvLevel, windSpeed;

        ImageView morningWeather, eveningWeather;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.txtDayOfWeek);
            date = itemView.findViewById(R.id.txtDate);
            highTemp = itemView.findViewById(R.id.txtHighTemp);
            lowTemp = itemView.findViewById(R.id.txtLowTemp);
            humidityPercent = itemView.findViewById(R.id.txtHumidity);
            uvLevel = itemView.findViewById(R.id.txtUV);
            windSpeed = itemView.findViewById(R.id.txtWind);
            morningWeather = itemView.findViewById(R.id.imgMorningWeather);
            eveningWeather = itemView.findViewById(R.id.imgEveningWeather);


        }
    }
}
