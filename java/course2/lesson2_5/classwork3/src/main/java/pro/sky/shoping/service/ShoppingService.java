package pro.sky.shoping.service;

import java.util.List;

public interface ShoppingService {
    boolean addToShoppingList(String item);

    boolean removeFromShoppingList(String item);

    List<String> getShoppingList();
}
