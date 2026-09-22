package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service 
public class WeatherService {

    @Cacheable(value = "weather", key = "#city")
    public String getWeatherByCity(String city) throws IOException, InterruptedException{
        System.out.println("Fetching weather data for city: " + city);
       String a = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric"+"&appid="+getApiKey();
       System.out.println(a);
       HttpClient client=HttpClient.newHttpClient();
       HttpRequest httpRequest=HttpRequest.newBuilder()
               .uri(URI.create(a))
               .GET()
               .build();
       HttpResponse<String>response=client.send(httpRequest,HttpResponse.BodyHandlers.ofString());
       return response.body();

    }
    public String getApiKey(){
        return System.getenv("WEATHER_API_KEY");
    }
   @CacheEvict(value = "weather", key = "#city")
   public void cleanWeatherCache(String city){
    System.out.println("Cache for city " + city + " has been cleared.");
   }
   @CachePut(value = "weather", key = "#city")
    public String updateWeatherCache(String city) throws IOException, InterruptedException{
        System.out.println("Updating cache for city: " + city);
        return getWeatherByCity(city);
     }
}

