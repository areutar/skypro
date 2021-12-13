package pro.sky.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/add")
    public void add(@RequestParam List<Integer> ids) {
        storeService.add(ids);
    }

    @GetMapping("/get")
    public List<Integer> getIds() {
        return storeService.getIds();
    }

}
