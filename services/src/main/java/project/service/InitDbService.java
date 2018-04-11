package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.CategoryEntity;
import project.entity.ProductEntity;
import project.repository.CategoryRepository;
import project.repository.ProductRepository;

import javax.annotation.PostConstruct;

@Service
public class InitDbService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        CategoryEntity category = categoryRepository.save(new CategoryEntity("Телефоны"));

        productRepository.save(new ProductEntity("Iphone 7", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", category));
        productRepository.save(new ProductEntity("Iphone 8", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", category));
        productRepository.save(new ProductEntity("Iphone X", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", category));

        category = categoryRepository.save(new CategoryEntity("Телевизоры"));

        category = categoryRepository.save(new CategoryEntity("Прочее"));
    }

}
