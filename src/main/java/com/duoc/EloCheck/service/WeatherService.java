package com.duoc.EloCheck.service;

import com.duoc.EloCheck.dto.ResponseWeatherDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {
     private final WebClient webClient;

    @Value("${openmeteo.base-url}")
    private String baseUrl;

    @Value("${openmeteo.default-latitude}")
    private double defaultLatitude;

    @Value("${openmeteo.default-longitude}")
    private double defaultLongitude;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public ResponseWeatherDto getCurrentWeather(Double latitude, Double longitude) {
        double lat = (latitude != null) ? latitude : defaultLatitude;
        double lon = (longitude != null) ? longitude : defaultLongitude;

        return webClient.get()
                .uri(baseUrl + "?latitude={lat}&longitude={lon}&current_weather=true",
                        lat, lon)
                .retrieve()
                .bodyToMono(ResponseWeatherDto.class)
                .block(); // bloqueo síncrono, ideal para REST tradicional
    }
}
