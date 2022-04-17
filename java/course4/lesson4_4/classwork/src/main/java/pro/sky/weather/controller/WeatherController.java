package pro.sky.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.weather.model.Weather;
import pro.sky.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<Weather> getWeather(@PathVariable("city") String city) {
        return ResponseEntity.ok(weatherService.getWeather(city));
    }

    @GetMapping("/greet")
    public String getWeather() {
        return "ResponseEntity.ok(weatherService.getWeather(city))";
    }
}
