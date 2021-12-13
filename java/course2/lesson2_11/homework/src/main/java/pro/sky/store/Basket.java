package pro.sky.store;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket {

    private final List<Integer> ids = new ArrayList<>();
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return name.equals(basket.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public List<Integer> getIds() {
        return ids;
    }

}
