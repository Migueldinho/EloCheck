package com.duoc.EloCheck.service;

import com.duoc.EloCheck.dto.ResponseWeatherDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    // ✅ Apunta exactamente al Bean de WebClientConfig
    private final WebClient webClient;

    @Value("${openmeteo.default-latitude}")
    private double defaultLatitude;

    @Value("${openmeteo.default-longitude}")
    private double defaultLongitude;

    public WeatherService(@Qualifier("weatherWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseWeatherDto getCurrentWeather(Double latitude, Double longitude) {
        double lat = (latitude != null) ? latitude : defaultLatitude;
        double lon = (longitude != null) ? longitude : defaultLongitude;

        // ✅ La baseUrl ya está en el Bean, solo agregamos query params
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", lat)
                        .queryParam("longitude", lon)
                        .queryParam("current_weather", true)
                        .build())
                .retrieve()
                .bodyToMono(ResponseWeatherDto.class)
                .block();
    }
}