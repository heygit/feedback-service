package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.external.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private TranscationService transcationService;
    @Autowired
    private BlockService blockService;

    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Iphone 7", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", 3));
        products.add(new Product(2, "Iphone 8", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", 2));
        products.add(new Product(3, "Iphone X", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", 3));
    }

    public List<Product> getProcucts() {
        return products;
    }

    public Product getProcuctById(long productId) {
        return products.stream().filter(elem -> elem.getId() == productId).findAny().get();
    }

    public List<Product> getProcuctsByCategory(long categoryId) {
        return Collections.singletonList(products.get(0));
    }

}
