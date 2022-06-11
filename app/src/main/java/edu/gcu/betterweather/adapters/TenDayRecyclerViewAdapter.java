package edu.gcu.betterweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.gcu.betterweather.R;


public class TenDayRecyclerViewAdapter extends RecyclerView.Adapter<TenDayRecyclerViewAdapter.ViewHolder> {


    private final OnWeatherClickListener onWeatherClickListener;

    private final ArrayList<String> dates;
    private final ArrayList<String> highTemps;
    private final ArrayList<String> lowTemps;


    public TenDayRecyclerViewAdapter
            (ArrayList<String> dates,
             ArrayList<String> highTemps,
             ArrayList<String> lowTemps,
             OnWeatherClickListener onWeatherClickListener)
    {

        this.dates = dates;
        this.highTemps = highTemps;
        this.lowTemps = lowTemps;
        this.onWeatherClickListener = onWeatherClickListener;
    }


    @NonNull
    @Override
    public TenDayRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_day_weather, parent, false);
        return new ViewHolder(view, onWeatherClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.date.setText(dates.get(position));
        holder.highTemp.setText(highTemps.get(position));
        holder.lowTemp.setText(lowTemps.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            sunrise = itemView.findViewById(R.id.txtSunriseTD);
            sunset = itemView.findViewById(R.id.txtSunsetTD);

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