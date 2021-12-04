package pro.sky.shoping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.shoping.service.ShoppingService;
import pro.sky.shoping.service.ShoppingSetService;

import java.util.List;
import java.util.Set;

@RestController
public class ShoppingsSetController {
    private final ShoppingSetService shoppingSetService;

    public ShoppingsSetController(ShoppingSetService shoppingSetService) {
        this.shoppingSetService = shoppingSetService;
    }
    @GetMapping(path = "post/shopping-set")
    public boolean addToShoppingList(String item) {
        return shoppingSetService.addToShoppingSet(item);
    }

    @GetMapping(path = "delete/shopping-set")
    public boolean removeFromShoppingList(String item) {
        return shoppingSetService.removeFromShoppingSet(item);
    }

    @GetMapping(path = "get/shopping-set")
    public Set<String> getShoppingList() {
        return shoppingSetService.getShoppingSet();
    }
}
