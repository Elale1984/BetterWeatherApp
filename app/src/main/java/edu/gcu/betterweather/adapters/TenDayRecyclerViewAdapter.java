package edu.gcu.betterweather.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.gcu.betterweather.R;


public class TenDayRecyclerViewAdapter extends RecyclerView.Adapter<TenDayRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private OnWeatherClickListener onWeatherClickListener;

    private ArrayList<String> dates;
    private ArrayList<String> highTemps;
    private ArrayList<String> lowTemps;
    private ArrayList<String> humidityPercents;
    private ArrayList<String> windsSpeeds;
    private ArrayList<String> uvLevels;
    private ArrayList<String> sunrises;
    private ArrayList<String> sunsets;

    private Context context;


    public TenDayRecyclerViewAdapter
            (Context context,
             ArrayList<String> dates,
             ArrayList<String> highTemps,
             ArrayList<String> lowTemps,
             ArrayList<String> humidityPercents,
             ArrayList<String> windsSpeeds,
             ArrayList<String> uvLevels,
             ArrayList<String> sunrises,
             ArrayList<String> sunsets,
             OnWeatherClickListener onWeatherClickListener)
    {

        this.context = context;
        this.dates = dates;
        this.highTemps = highTemps;
        this.lowTemps = lowTemps;
        this.humidityPercents = humidityPercents;
        this.windsSpeeds = windsSpeeds;
        this.uvLevels = uvLevels;
        this.sunrises = sunrises;
        this.sunsets = sunsets;
        this.onWeatherClickListener = onWeatherClickListener;
    }


    @Override
    public TenDayRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: called");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_day_weather, parent, false);
        return new ViewHolder(view, onWeatherClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.date.setText(dates.get(position));
        holder.highTemp.setText(highTemps.get(position));
        holder.lowTemp.setText(lowTemps.get(position));
        // holder.sunrise.setText(sunrises.get(position));
        // holder.sunset.setText(sunsets.get(position));


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnWeatherClickListener onWeatherClickListener;
        TextView dayOfWeek, date, highTemp, lowTemp,
                humidityPercent, uvLevel, windSpeed, sunrise, sunset;

        ImageView morningWeather, eveningWeather;


        public ViewHolder(View itemView, OnWeatherClickListener onWeatherClickListener) {
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
            /*
            sunrise = itemView.findViewById(R.id.txtSunriseRecycle);
            sunset = itemView.findViewById(R.id.txtSunsetRecycle);
             */

            this.onWeatherClickListener = onWeatherClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onWeatherClickListener.onDayClickListener(getAbsoluteAdapterPosition());
        }
    }
    public interface OnWeatherClickListener{
        void onDayClickListener(int position);
    }
}