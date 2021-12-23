package pro.sky.store;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    private final Basket sessionBasket;

    public StoreServiceImpl(Basket sessionBasket) {
        this.sessionBasket = sessionBasket;
    }


    @Override
    public void add(List<Integer> ids) {
        sessionBasket.addIds(ids);
    }

    @Override
    public List<Integer> getIds() {
        return sessionBasket.getIds();
    }
}
