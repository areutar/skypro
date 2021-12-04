package pro.sky.shoping.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.shoping.service.ShoppingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListImpl implements ShoppingService {
    private List<String> shoppingList;

    public ShoppingListImpl() {
        shoppingList = new ArrayList<>();
    }

    @Override
    public boolean addToShoppingList(String item) {
        if (shoppingList.contains(item)) {
            return false;
        }
        return shoppingList.add(item);
    }

    @Override
    public boolean removeFromShoppingList(String item) {
        return shoppingList.remove(item);
    }

    @Override
    public List<String> getShoppingList() {
        return shoppingList;
    }
}
