package pro.sky.weather.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeatherMain {
    private BigDecimal temp;
    private BigDecimal humidity;
}
