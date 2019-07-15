package com.example.breatheapp.util;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.Calendar;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs (Activity activity) {

        // private saved data so only this app can see it/use it
        this.preferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }



    public void setBreaths (int breaths) {
        // saving breaths to system files
        preferences.edit().putInt("breaths",breaths).apply();

    }
    public int getBreaths () {
        return preferences.getInt("breaths",0);
    }

    public void setSessons (int session) {
        preferences.edit().putInt("session",session).apply();

    }
    public int getSessions () {
        return preferences.getInt("session",0);
    }

    public void setDate(long milliseconds) {
        preferences.edit().putLong("seconds",milliseconds).apply();
    }

    public String getDate () {
        long milliDate = preferences.getLong("seconds", 0);
        String amOrPm;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliDate);


        int a = calendar.get(Calendar.AM_PM);
        if (a == Calendar.AM) {
            amOrPm = "AM";

        }else  {
            amOrPm = "PM";
        }

        String minutes;
        int min = calendar.get(Calendar.MINUTE);
        if (min < 10) {
             minutes = "0" + min;
        }else {
            minutes = String.valueOf(min);
        }
        String time =  calendar.get(Calendar.HOUR_OF_DAY) + ":" + minutes  + amOrPm;

        return time;



    }

}
