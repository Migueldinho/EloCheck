package com.duoc.EloCheck.controller;

import com.duoc.EloCheck.dto.ResponseWeatherDto;
import com.duoc.EloCheck.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/weather")

public class WeatherController {
private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // GET /api/weather/current → usa coordenadas por defecto (application.properties)
    @GetMapping("/current")
    public ResponseEntity<ResponseWeatherDto> getCurrentWeather() {
        return ResponseEntity.ok(weatherService.getCurrentWeather(null, null));
    }

    // GET /api/weather/current?latitude=-33.45&longitude=-70.67 → coordenadas personalizadas
    @GetMapping("/current/custom")
    public ResponseEntity<ResponseWeatherDto> getCurrentWeatherCustom(
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(latitude, longitude));
    }
    
}
