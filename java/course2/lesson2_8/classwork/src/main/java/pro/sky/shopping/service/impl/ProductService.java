package pro.sky.shopping.service.impl;

import java.util.List;

public interface ProductService {
    List<String> getProductNamesByTypeWithString(String type, String string);

    void changeNameByName(String forName, String toName);

    String getProductNameByType(String type);

    List<String> getProductNamesByType(String type);

    List<String> getAllProductsName();

    long countProductsByType(String type);

    boolean addProduct(String name, String type);

    boolean containsProduct(String name, String type);

    boolean removeProduct(String name, String type);
}
