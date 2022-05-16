package edu.gcu.betterweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


/*
   TODO: 5/15/2022 We will need to create click options possibly later for expanding data for
   TODO: each day on 10 day forecast.
*/


public class DayWeatherAdapter extends ArrayAdapter<DayWeather>  {

    private final int lastPosition = -1;
    private ArrayList<DayWeather> mDayWeatherData;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView dayOfWeek;
        ImageView morningWeather;
        ImageView eveningWeather;
        TextView lowTemp;
        TextView highTemp;

    }

    public DayWeatherAdapter(ArrayList<DayWeather> dayWeatherData, Context context) {
        super(context, R.layout.day_card_weather_layout, dayWeatherData);
        this.mDayWeatherData= dayWeatherData;
        this.mContext = context;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //Get data for current position
        DayWeather dayWeather = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        final View outView;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.day_card_weather_layout, parent, false);
            viewHolder.dayOfWeek = (TextView) convertView.findViewById(R.id.txtWeekDay);
            viewHolder.morningWeather = (ImageView) convertView.findViewById(R.id.ivWeatherIconDay);
            viewHolder.eveningWeather = (ImageView) convertView.findViewById(R.id.ivWeatherIconNight);
            viewHolder.lowTemp = (TextView) convertView.findViewById(R.id.txtTempLow);
            viewHolder.highTemp = (TextView) convertView.findViewById(R.id.txtTempHigh);

            outView=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            outView=convertView;
        }
        return convertView;
    }
}
