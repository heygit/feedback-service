package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.CategoryEntity;
import project.entity.ProductEntity;
import project.repository.AccountRepository;
import project.repository.CategoryRepository;
import project.repository.ProductRepository;

import javax.annotation.PostConstruct;

@Service
public class InitDbService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void init() {
        CategoryEntity category = categoryRepository.save(new CategoryEntity("Телефоны"));

        productRepository.save(new ProductEntity("Iphone 7", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4.7 дюйма и технологией IPS\n" +
                "Multi‑Touch с технологией IPS",
                "https://avatars.mds.yandex.net/get-mpic/397397/img_id8407956531717956576.jpeg/9hq", category));
        productRepository.save(new ProductEntity("Iphone 8", "Дисплей Retina HD\n" +
                "Широкоформатный ЖК‑дисплей Multi‑Touch с диагональю 4.7 дюйма и технологией IPS\n" +
                "Пыле-влаго защита",
                "https://avatars.mds.yandex.net/get-mpic/200316/img_id6510098813018604250.jpeg/9hq", category));
        productRepository.save(new ProductEntity("Iphone X", "Широкоформатный OLED дисплей" +
                " с диагональю 5.8 дюйма и разрешением 2436x1125\n" +
                "двойная камера 12/12 МП, автофокус, F/1.8",
                "https://avatars.mds.yandex.net/get-mpic/200316/img_id270362589725797013.png/9hq", category));

        category = categoryRepository.save(new CategoryEntity("Телевизоры"));

        productRepository.save(new ProductEntity("LG 49SJ810V",
                "ЖК-телевизор, 4K UHD\n" +
                "диагональ 49.5\" (126 см), TFT IPS\n" +
                "Smart TV (webOS), Wi-Fi\n" +
                "тип подсветки: Edge LED",
                "https://avatars.mds.yandex.net/get-mpic/199079/img_id8976854896416167596/9hq", category));
        productRepository.save(new ProductEntity("Samsung UE49MU6300U", "ЖК-телевизор, 4K UHD\n" +
                "диагональ 49\" (124 см) Smart TV (Tizen), Wi-Fi HDMI x3, USB x2, DVB-T2\n" +
                "изогнутый экран, поддержка HDR, 2 TV-тюнера",
                "https://avatars.mds.yandex.net/get-mpic/195452/img_id7471033699703077539/9hq", category));
        productRepository.save(new ProductEntity("LG 43UJ651V", "ЖК-телевизор, 4K UHD" +
                "диагональ 42.5\" (108 см), TFT IPS Smart TV (webOS), Wi-Fi HDMI x4, USB x2, DVB-T2, \n" +
                "тип подсветки: Direct LED, 2 TV-тюнера",
                "https://avatars.mds.yandex.net/get-mpic/372220/img_id1501835863116301726/9hq", category));

        category = categoryRepository.save(new CategoryEntity("Прочее"));

        productRepository.save(new ProductEntity("Polaris PCM 4003AL\n", "кофемашина-эспрессо\n" +
                "полуавтоматическая для молотого кофе\n" +
                "приготовление капучино; защита от перегрева; трафареты для рисунков на молочной пене; мерная ложечка",
                "https://avatars.mds.yandex.net/get-mpic/175985/img_id3292059662984109492.jpeg/9hq", category));

        accountService.register("111111", "111111");
        accountService.register("222222", "222222");
        accountService.register("333333", "333333");
    }
}
