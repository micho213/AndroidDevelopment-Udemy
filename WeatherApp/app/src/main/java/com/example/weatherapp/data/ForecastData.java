package com.example.weatherapp.data;

import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.weatherapp.controller.AppController;
import com.example.weatherapp.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ForecastData {

    ArrayList<Forecast> forecastArrayList = new ArrayList<>();

    private String urlTemplate = "https://api.apixu.com/v1/forecast.json?key=b56b82ed07624f9bae1122058190507&q=";

    public void getForecast(final ForecastListAsyncResponse callback , String locationTxt){

        String url = urlTemplate + locationTxt + "&days=7";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    JSONObject x = response.getJSONObject("forecast");
                    JSONArray forecastArray = x.getJSONArray("forecastday");

                    for (int i = 0; i < forecastArray.length(); i ++) {
                        Forecast forecast = new Forecast();

                        JSONObject forecastObject = forecastArray.getJSONObject(i);

                        JSONObject forecastDay = forecastObject.getJSONObject("day");
                        JSONObject forecastDesc = forecastDay.getJSONObject("condition");

                        forecast.setForecastDate(forecastObject.getString("date"));


                        forecast.setForecastHighTemp(forecastDay.getString("maxtemp_c"));
                        forecast.setForecastLowTemp(forecastDay.getString("mintemp_c"));
                        forecast.setForecastTemp(forecastDay.getString("avgtemp_c"));
                        forecast.setForecastWeatherDesc(forecastDesc.getString("text"));
                        forecast.setForecastIcon(forecastDesc.getString("icon"));


                        JSONObject location = response.getJSONObject("location");

                        // setting the info about the place
                        forecast.setCity(location.getString("name"));
                        forecast.setCountry(location.getString("country"));
                        forecast.setRegion(location.getString("region"));

                        // current weather

                        JSONObject current = response.getJSONObject("current");
                        JSONObject desc = current.getJSONObject("condition");

                        forecast.setDate(location.getString("localtime"));
                        forecast.setCurrentTemperature(current.getString("temp_c"));
                        forecast.setCurrentWeatherDesc(desc.getString("text"));
                        forecast.setCurrentIcon(desc.getString("icon"));


                        forecastArrayList.add(forecast);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if( callback != null) {
                    callback.processFinished(forecastArrayList);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);


    }




}
