package pro.sky.shoping.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.shoping.service.ShoppingService;
import pro.sky.shoping.service.ShoppingSetService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShoppingsSetImpl implements ShoppingSetService {
    private Set<String> shoppingSet;

    public ShoppingsSetImpl() {
        shoppingSet = new HashSet<>();
    }


    @Override
    public boolean addToShoppingSet(String item) {
        return shoppingSet.add(item);
    }

    @Override
    public boolean removeFromShoppingSet(String item) {
        return shoppingSet.remove(item);
    }

    @Override
    public Set<String> getShoppingSet() {
        return shoppingSet;
    }
}
