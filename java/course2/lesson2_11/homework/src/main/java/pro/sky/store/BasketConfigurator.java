package pro.sky.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BasketConfigurator {
    @Bean
    @SessionScope
    public Basket sessionBasket() {
        return new Basket();
    }
}
