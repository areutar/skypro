package pro.sky.shopping.model;

import java.util.Objects;

public class Product {
    private String type;
    private String name;

    public Product(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(type, product.type) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
