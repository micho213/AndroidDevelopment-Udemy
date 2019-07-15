package com.example.weatherapp.model;

import java.io.Serializable;

public class Forecast implements Serializable {


    // location object
    private  String city;
    private String country;
    private String region;

    // current condition object

    private String date;
    private String currentTemperature;
    private String currentWeatherDesc;

    // forectast object
    private String forecastDate;
    private String forecastDay;
    private String forecastHighTemp;
    private String forecastLowTemp;
    private String forecastWeatherDesc;
    private String forecastTemp;


    // Icon
    private String currentIcon;
    private String forecastIcon;

    // description HTML
    private String descHTML;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getCurrentWeatherDesc() {
        return currentWeatherDesc;
    }

    public void setCurrentWeatherDesc(String currentWeatherDesc) {
        this.currentWeatherDesc = currentWeatherDesc;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(String forecastDay) {
        this.forecastDay = forecastDay;
    }

    public String getForecastHighTemp() {
        return forecastHighTemp;
    }

    public void setForecastHighTemp(String forecastHighTemp) {
        this.forecastHighTemp = forecastHighTemp;
    }

    public String getForecastLowTemp() {
        return forecastLowTemp;
    }

    public void setForecastLowTemp(String forecastLowTemp) {
        this.forecastLowTemp = forecastLowTemp;
    }

    public String getForecastWeatherDesc() {
        return forecastWeatherDesc;
    }

    public void setForecastWeatherDesc(String forecastWeatherDesc) {
        this.forecastWeatherDesc = forecastWeatherDesc;
    }

    public String getDescHTML() {
        return descHTML;
    }

    public void setDescHTML(String descHTML) {
        this.descHTML = descHTML;
    }

    public String getForecastTemp() {
        return forecastTemp;
    }

    public void setForecastTemp(String forecastTemp) {
        this.forecastTemp = forecastTemp;
    }

    public String getCurrentIcon() {
        return currentIcon;
    }

    public void setCurrentIcon(String currentIcon) {
        this.currentIcon = currentIcon.substring(2);
    }

    public String getForecastIcon() {
        return forecastIcon;
    }

    public void setForecastIcon(String forecastIcon) {
        this.forecastIcon = forecastIcon.substring(2);
    }
}
