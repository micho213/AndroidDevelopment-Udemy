package com.example.weatherapp;


import android.os.Build;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.model.Forecast;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {


    public ForecastFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Forecast forecast =  (Forecast) getArguments().getSerializable("forecast");

        View forecastView = inflater.inflate(R.layout.fragment_forecast,container,false);

        ImageView forecastIcon = forecastView.findViewById(R.id.forecastIconId);
        TextView forecastTemp = forecastView.findViewById(R.id.forecastTemp);
        TextView forecastDate = forecastView.findViewById(R.id.forecastDate);
        TextView forecastHigh = forecastView.findViewById(R.id.forecastHigh);
        TextView forecastLow = forecastView.findViewById(R.id.forecastLow);
        TextView forecastDesc = forecastView.findViewById(R.id.forecastDesc);





        Picasso.get().load("https://" + forecast.getForecastIcon()).into(forecastIcon);

        forecastTemp.setText(forecast.getForecastTemp() + " C");
        forecastDate.setText(forecast.getForecastDate());

        forecastHigh.setText(forecast.getForecastHighTemp() + " C");
        forecastLow.setText(forecast.getForecastLowTemp()+ " C");
        forecastDesc.setText(forecast.getForecastWeatherDesc());
        forecastView.invalidate();



        return forecastView;
    }

    public static final ForecastFragment newInstance(Forecast forecast) {
        ForecastFragment forecastFragment = new ForecastFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("forecast", forecast);

        forecastFragment.setArguments(bundle);

        return forecastFragment;

    }

}
