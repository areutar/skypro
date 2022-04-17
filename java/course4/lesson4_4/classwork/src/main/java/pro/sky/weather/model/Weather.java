package pro.sky.weather.model;

import lombok.Data;

@Data
public class Weather {
    private WeatherMain main;
    private WeatherWind wind;
}
