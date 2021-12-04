package pro.sky.shoping.service;

import java.util.Set;

public interface ShoppingSetService {
    boolean addToShoppingSet(String item);

    boolean removeFromShoppingSet(String item);

    Set<String> getShoppingSet();
}
