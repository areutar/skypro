package pro.sky.store;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Resource (name = "sessionBasket")
    Basket sessionBasket;

    @Override
    public void add(List<Integer> ids) {
        sessionBasket.getIds().addAll(ids);
    }

    @Override
    public List<Integer> getIds() {
        return sessionBasket.getIds();
    }
}
