package pro.sky.shopping.service.impl;

import java.util.List;

public interface ProductService {
    List<String> getProductNameByType(String type);

    List<String> getAllProductsName();

    int countProductsByType(String type);

    boolean addProduct(String name, String type);

    boolean containsProduct(String name, String type);

    boolean removeProduct(String name, String type);
}
