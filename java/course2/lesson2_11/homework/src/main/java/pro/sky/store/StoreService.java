package pro.sky.store;

import java.util.List;

public interface StoreService {
    void add(List<Integer> ids);

    List<Integer> getIds();
}
