package pro.sky.java.course2;

import pro.sky.java.course2.service.GreetingsService;

public class Application {
    public static void main(String[] args) {
        GreetingsService service = new GreetingsService();
        System.out.println(service.generateGreetings());
    }
}
