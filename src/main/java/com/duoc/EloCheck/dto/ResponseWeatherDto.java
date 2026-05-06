package com.duoc.EloCheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseWeatherDto {
    private double latitude;
    private double longitude;

    @JsonProperty("current_weather")
    private CurrentWeatherDto currentWeather;

    // Getters y Setters
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public CurrentWeatherDto getCurrentWeather() { return currentWeather; }
    public void setCurrentWeather(CurrentWeatherDto currentWeather) { this.currentWeather = currentWeather; }
}
