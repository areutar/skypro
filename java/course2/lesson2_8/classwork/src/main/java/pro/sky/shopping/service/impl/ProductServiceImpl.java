package pro.sky.shopping.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.shopping.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getProductNamesByType(String type) {
        return products.stream()
                .filter(product -> product.getType().equals(type))
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllProductsName() {
        return products.stream()
                .map(Product::getName)
                .sorted(String::compareTo)
                .collect(Collectors.toList());
    }

    @Override
    public long countProductsByType(String type) {
        return products.stream()
                .filter(product -> product.getType().equals(type))
                .count();

    }

    @Override
    public String getProductNameByType(String type) {
        return products.stream()
                .filter(product -> product.getType().equals(type))
                .map(Product::getName)
                .findFirst()
                .orElse("Not Found");
    }

    @Override
    public void changeNameByName(String forName, String toName) {
        products.stream()
                .filter(product -> product.getName().equals(forName))
                .forEach(product -> product.setName(toName));
    }

    @Override
    public List<String> getProductNamesByTypeWithString(String type, String string) {
        return products.stream()
                .filter(product -> product.getType().equals(type))
                .map(product -> string + " " + product.getName())
                .collect(Collectors.toList());
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
