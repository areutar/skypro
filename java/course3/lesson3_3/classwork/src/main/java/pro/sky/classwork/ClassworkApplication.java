package pro.sky.classwork;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ClassworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassworkApplication.class, args);
    }

}
