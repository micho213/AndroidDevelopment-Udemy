package com.example.weatherapp.data;

import com.example.weatherapp.model.Forecast;

import java.util.ArrayList;

public interface ForecastListAsyncResponse {

    void processFinished(ArrayList<Forecast> forecastArrayList);

}
