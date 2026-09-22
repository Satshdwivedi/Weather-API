package com.example.demo;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/weather")
@RestController 
public class WeatherController {
private WeatherService weatherService;

public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
}
@GetMapping
public String weatheString(@RequestParam String city) throws IOException, InterruptedException {
    return weatherService.getWeatherByCity(city);
}
@DeleteMapping("/cache")
public void deleteWeatherCache(@RequestParam String city) {
    weatherService.cleanWeatherCache(city);
}
@PutMapping("/cache")
public String updateWeatherCache(@RequestParam String city) throws IOException, InterruptedException {
    return weatherService.updateWeatherCache(city);
}
}