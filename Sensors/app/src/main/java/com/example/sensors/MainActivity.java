package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sensors.view.CompassView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private TableLayout tableLayout;

    private Sensor mLight;
    private Sensor mTemp;
    private Sensor mAcc;
    private Sensor mGravity;



    private TextView lightView;
    private TextView tempView;


    private TextView x;
    private TextView y;
    private TextView z;

    private TextView gx;
    private TextView gy;
    private TextView gz;


    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tableLayout = findViewById(R.id.tableLayout);

        adjustTableSize();


        lightView = findViewById(R.id.light);

        tempView = findViewById(R.id.temp);


        gx = findViewById(R.id.gx);
        gy = findViewById(R.id.gy);
        gz = findViewById(R.id.gz);

        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

          for (Sensor sensor: sensors){
              Log.d("Sensor name: " , sensor.getName());
//              Log.d("Sensor type: " , sensor.getStringType());
          }


        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mAcc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);




    }

    private void adjustTableSize() {
        for (int i=0; i < tableLayout.getChildCount(); i ++){
            View v = tableLayout.getChildAt(i);

            if ( v instanceof TableRow){
                View title = ((TableRow) v).getChildAt(0);
                ((TextView) title).setTextColor(Color.MAGENTA);

                if(i == 0) {
                    ((TextView) title).setTextColor(Color.BLACK);
                }


                for (int j = 0; j < ((TableRow) v).getChildCount(); j ++){
                    View v1 = ((TableRow) v).getChildAt(j);

                    if ( v1 instanceof  TextView) {
                        ((TextView) v1).setTextSize(24);
                    }
                }

                Log.d("dasd","adasd");
                v.setMinimumHeight(120);
            }
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {



        if( sensorEvent.sensor.getType() == 5) {
            float lux = sensorEvent.values[0];

            lightView.setText(String.valueOf(lux));

        } else if (sensorEvent.sensor.getType() == 13) {
            float temp = sensorEvent.values[0];
            tempView.setText( String.valueOf(temp));
        }else if (sensorEvent.sensor.getType() == 1) {

            x.setText("X: " + (Math.round(sensorEvent.values[0] * 1000.0f) /1000.0f )) ;
            y.setText("Y: " + (Math.round(sensorEvent.values[1] * 1000.0f) /1000.0f )) ;
            z.setText("Z: " + (Math.round(sensorEvent.values[2] * 1000.0f) /1000.0f )) ;

        }else if ( sensorEvent.sensor.getType() == 9){

            gx.setText("X: " + (Math.round(sensorEvent.values[0] * 1000.0f) /1000.0f )) ;
            gy.setText("Y: " + (Math.round(sensorEvent.values[1] * 1000.0f) /1000.0f )) ;
            gz.setText("Z: " + (Math.round(sensorEvent.values[2] * 1000.0f) /1000.0f )) ;
        }




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAcc, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mGravity,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    public void launchCompass(View view) {

        Intent myIntent = new Intent(this, CompassActivity.class);
        this.startActivity(myIntent);


    }
}
