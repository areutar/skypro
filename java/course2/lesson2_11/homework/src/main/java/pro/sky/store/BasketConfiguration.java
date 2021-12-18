package pro.sky.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BasketConfiguration {
    @Bean
    @SessionScope
    public Basket sessionBasket() {
        return new Basket();
    }

}
