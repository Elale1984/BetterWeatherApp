package edu.gcu.betterweather;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class WeatherRetrieve {



    public static ArrayList<WeatherData> requestForecast(String location) throws Exception {
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
                rawResult=EntityUtils.toString(entity, Charset.forName("utf-8"));
            }


        } finally {
            response.close();
        }

        return parseTimelineJson(rawResult);

    }
    private static ArrayList<WeatherData> parseTimelineJson(String rawResult) {

        if (rawResult==null || rawResult.isEmpty()) {
            System.out.printf("No raw data%n");
            return;
        }

        JSONObject timelineResponse = new JSONObject(rawResult);

        // return timelineResponse;

        ZoneId zoneId=ZoneId.of(timelineResponse.getString("timezone"));

        JSONArray values=timelineResponse.getJSONArray("days");

        ArrayList<WeatherData> forecastSet = new ArrayList<WeatherData>();

        for (int i = 0; i < 10, i++) // get the first ten days of forecasts
        {
            JSONObject dayValue = values.getJsonObject(i);

            ZonedDateTime datetime=ZonedDateTime.ofInstant(Instant.ofEpochSecond(dayValue.getLong("datetimeEpoch")), zoneId);

            String maxtemp = dayValue.getString("tempmax");
            String mintemp = dayValue.getString("tempmin");
            String sunrise = dayValue.getString("sunrise");
            String sunset = dayValue.getString("sunset");
            String uvindex = dayValue.getString("uvindex");
            String humidity = dayValue.getString("humidity");
            String windspeed = dayValue.getString("windspeed")

            WeatherData newForecast = new WeatherData(maxtemp, mintemp, sunrise, sunset, uvindex, humidity, windspeed);
            forecastSet.add(newForecast);
        }

        return forecastSet;
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