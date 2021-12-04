package pro.sky.shoping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.shoping.service.ShoppingService;

import java.util.List;

@RestController
public class ShoppingController {
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
    @GetMapping(path = "post/shopping-list")
    public boolean addToShoppingList(String item) {
        return shoppingService.addToShoppingList(item);
    }

    @GetMapping(path = "delete/shopping-list")
    public boolean removeFromShoppingList(String item) {
        return shoppingService.removeFromShoppingList(item);
    }

    @GetMapping(path = "get/shopping-list")
    public List<String> getShoppingList() {
        return shoppingService.getShoppingList();
    }
}
