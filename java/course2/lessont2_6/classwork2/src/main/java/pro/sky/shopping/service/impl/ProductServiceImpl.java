package pro.sky.shopping.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.shopping.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
        products.add(new Product("еда", "хлеб"));
        products.add(new Product("еда", "колбаса"));
        products.add(new Product("еда", "йогурт"));
        products.add(new Product("техника", "телефон"));
        products.add(new Product("техника", "телефон"));
        products.add(new Product("техника", "телефон"));
        products.add(new Product("техника", "планшет"));
    }

    @Override
    public List<String> getProductNameByType(String type) {
        List<String> searchResult = new ArrayList<>();
        for (Product product : products) {
            if (product.getType().equals(type)) {
                searchResult.add(product.getName());
            }
        }
        return searchResult;
    }

    @Override
    public List<String> getAllProductsName() {
        List<String> searchResult = new ArrayList<>();
        for (Product product : products) {
            searchResult.add(product.getName());
        }
        searchResult.sort(String::compareTo);
        Collections.sort(searchResult);
        return searchResult;
    }

    @Override
    public int countProductsByType(String type) {
        int count = 0;
        for (Product product : products) {
            if (product.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean addProduct(String name, String type) {
        return products.add(new Product(type, name));
    }

    @Override
    public boolean containsProduct(String name, String type) {
        return products.contains(new Product(type, name));
    }

    @Override
    public boolean removeProduct(String name, String type) {
        return products.remove(new Product(type, name));
    }
}
