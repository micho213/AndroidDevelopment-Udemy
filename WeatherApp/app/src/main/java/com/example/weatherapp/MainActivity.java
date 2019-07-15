package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.data.ForecastData;
import com.example.weatherapp.data.ForecastListAsyncResponse;
import com.example.weatherapp.data.ForecastViewPagerAdapter;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.util.Prefs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ForecastViewPagerAdapter forecastViewPagerAdapter;
    private ViewPager viewPager;
    private TextView locationText;
    private TextView currentTempText;
    private TextView currentDate;
    private EditText userLocation;
    private String enteredLocation;
    private ImageView currentIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationText = findViewById(R.id.locationText);
        currentTempText = findViewById(R.id.currentTempId);
        currentDate = findViewById(R.id.currentDate);
        userLocation = findViewById(R.id.locationName);
        currentIcon = findViewById(R.id.weatherIcon);
        viewPager = findViewById(R.id.viewPager);


        final Prefs prefs = new Prefs(this);
        String defaultLocation = prefs.getLocation();
        weather(defaultLocation);

        userLocation.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
                if ( (keyEvent.getAction() == KeyEvent.ACTION_DOWN) && keycode == keyEvent.KEYCODE_ENTER) {

                    Toast.makeText(getApplicationContext(), userLocation.getText().toString().trim(),Toast.LENGTH_LONG).show();
                    enteredLocation = userLocation.getText().toString().trim();
                    prefs.setLocation(enteredLocation);
                    weather(enteredLocation);

                    return true;
                }
                return false;

            }
        });


        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(forecastViewPagerAdapter);

    }

    private void weather(String location) {
        enteredLocation = userLocation.getText().toString().trim();
        forecastViewPagerAdapter = new ForecastViewPagerAdapter(getSupportFragmentManager(), getFragments(location));
        viewPager.invalidate();
        viewPager.setAdapter(forecastViewPagerAdapter);



    }


    private List<Fragment> getFragments(String locationTxt){
        final List<Fragment> fragmentList = new ArrayList<>();

        new ForecastData().getForecast(new ForecastListAsyncResponse() {

            public void processFinished(ArrayList<Forecast> forecastArrayList) {

                locationText.setText(forecastArrayList.get(0).getCity() + ", \n" +  forecastArrayList.get(0).getRegion() + " " + forecastArrayList.get(0).getCountry());
                currentTempText.setText(forecastArrayList.get(0).getCurrentTemperature() + " C");
                currentDate.setText(forecastArrayList.get(0).getDate());
                Picasso.get().load("https://" +forecastArrayList.get(0).getCurrentIcon()).into(currentIcon);


                for (int i =0; i < forecastArrayList.size(); i ++ ) {
                    ForecastFragment forecastFragment =
                            ForecastFragment.newInstance(forecastArrayList.get(i));

                    fragmentList.add(forecastFragment);
                }
                forecastViewPagerAdapter.notifyDataSetChanged();
            }
        },locationTxt);

        return fragmentList;
    }

}
