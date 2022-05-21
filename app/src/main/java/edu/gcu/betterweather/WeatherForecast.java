package edu.gcu.betterweather;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.URIBuilder;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.CloseableHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClients;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeatherForecast {

    public static ArrayList<BWAData> forecast = new ArrayList();

    public String Location;

    public WeatherForecast() {
    }

    public WeatherForecast (String location)
    {
        Location = location;
        try {
            requestForecast(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestForecast(String location) throws Exception {
        //set up the end point
        String apiEndPoint="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
        String startDate=null;
        String endDate=null;

        String unitGroup="metric";
        String apiKey="9W8PBMYZLZRULGY57Q6BBLHN7"; //API key needed for weather retrieval

        StringBuilder requestBuilder=new StringBuilder(apiEndPoint);
        requestBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8.toString()));

        if (startDate!=null && !startDate.isEmpty()) {
            requestBuilder.append("/").append(startDate);
            if (endDate!=null && !endDate.isEmpty()) {
                requestBuilder.append("/").append(endDate);
            }
        }

        URIBuilder builder = new URIBuilder(requestBuilder.toString());

        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey);



        HttpGet get = new HttpGet(builder.build());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = httpclient.execute(get);

        String rawResult=null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
                return;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult= EntityUtils.toString(entity, Charset.forName("utf-8"));
            }


        } finally {
            response.close();
        }

        parseTimelineJson(rawResult);

    }
    private static void parseTimelineJson(String rawResult) {

        if (rawResult==null || rawResult.isEmpty()) {
            System.out.printf("No raw data%n");
            return;
        }
        try {
            JSONObject timelineResponse = new JSONObject(rawResult);

            //ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));

            JSONArray values = timelineResponse.getJSONArray("days");

            ArrayList<BWAData> forecastSet = new ArrayList<BWAData>();

            for (int i = 0; i < 10; i++) // get the first ten days of forecasts
            {
                JSONObject dayValue = values.getJSONObject(i);


                String date = dayValue.getString("datetime");
                String temp = dayValue.getString("temp");
                String maxtemp = dayValue.getString("tempmax");
                String mintemp = dayValue.getString("tempmin");
                String sunrise = dayValue.getString("sunrise");
                String sunset = dayValue.getString("sunset");
                String uvindex = dayValue.getString("uvindex");
                String humidity = dayValue.getString("humidity");
                String windspeed = dayValue.getString("windspeed");

                BWAData newForecast = new BWAData(date, temp, windspeed, uvindex, humidity, maxtemp, mintemp);
                forecastSet.add(newForecast);
            }
            forecast = forecastSet;
        }
        catch (Exception e)
        {

        }
        /*
        ZoneId zoneId=ZoneId.of(timelineResponse.getString("timezone"));

        System.out.printf("Weather data for: %s%n", timelineResponse.getString("resolvedAddress"));

        JSONArray values=timelineResponse.getJSONArray("days");

        System.out.printf("Date\tMaxTemp\tMinTemp\tPrecip\tSource%n");
        for (int i = 0; i < values.length(); i++) {
            JSONObject dayValue = values.getJSONObject(i);



            double maxtemp=dayValue.getDouble("tempmax");
            double mintemp=dayValue.getDouble("tempmin");
            double pop=dayValue.getDouble("precip");
            String source=dayValue.getString("source");
            System.out.printf("%s\t%.1f\t%.1f\t%.1f\t%s%n", datetime.format(DateTimeFormatter.ISO_LOCAL_DATE), maxtemp, mintemp, pop,source );
        } */
    }
}