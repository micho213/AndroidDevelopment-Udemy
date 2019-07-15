package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.view.CompassView;

public class MainActivity extends AppCompatActivity {

    private CompassView compassView;
    private SensorManager mSensorManager;
    private Sensor compassSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        compassView = new CompassView(this);
        setContentView(compassView);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        compassSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        if (compassSensor != null) {
            mSensorManager.registerListener(mySensorEventListener , compassSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            Log.d("Error", "orientation not found");
        }

    }

    private SensorEventListener mySensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float directions = sensorEvent.values[0];
            compassView.updateData(directions);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(compassSensor != null) {
            mSensorManager.unregisterListener(mySensorEventListener);
        }
    }
}
