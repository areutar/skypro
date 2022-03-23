package pro.sky.classwork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public String greetings() {
        return "<h1>Greet</h1>";
    }
}
