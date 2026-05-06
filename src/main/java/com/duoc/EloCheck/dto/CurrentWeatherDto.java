package com.duoc.EloCheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentWeatherDto {
    private double temperature; 
    private double velocidadViento;

    @JsonProperty("codigo meteorologico")
    private int codigoMetereologico; 

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getWindspeed() { return velocidadViento; }
    public void setWindspeed(double windspeed) { this.velocidadViento = windspeed; }

    public int getWeatherCode() { return codigoMetereologico; }
    public void setWeatherCode(int weatherCode) { this.codigoMetereologico = weatherCode; }
}



