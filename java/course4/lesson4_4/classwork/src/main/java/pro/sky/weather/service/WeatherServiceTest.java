package pro.sky.weather.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pro.sky.weather.model.Weather;
import pro.sky.weather.model.WeatherMain;
import pro.sky.weather.model.WeatherWind;

import java.math.BigDecimal;

@Service
@Profile("!production")
public class WeatherServiceTest implements WeatherService {
    @Override
    public Weather getWeather(String city) {
        Weather weather = new Weather();
        WeatherMain weatherMain = new WeatherMain();
        WeatherWind weatherWind = new WeatherWind();

        weatherMain.setHumidity(BigDecimal.ONE);
        weatherMain.setTemp(BigDecimal.TEN);
        weatherWind.setDeg(1);
        weatherWind.setSpeed(BigDecimal.ONE);

        weather.setMain(weatherMain);
        weather.setWind(weatherWind);
        return weather;
    }
}
