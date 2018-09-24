package server.electronics.product.domain;

import java.math.BigDecimal;
import java.util.Arrays;

public class ProductAndCategoryInitializer {

    public ProductAndCategoryInitializer(ProductRepository productRepository,
                                         CategoryRepository categoryRepository) {

        if (categoryRepository.count() != 0) {
            return;
        }

        Category laptops = new Category(1L, "Laptops");
        Category smartphones = new Category(2L, "Smartphones");
        Category cameras = new Category(3L,"Cameras");

        categoryRepository
                .saveAll(Arrays.asList(laptops, smartphones, cameras));

        if (productRepository.count() != 0){
            return;
        }

        Product predator = new Product(1L, "Acer Predator", new BigDecimal(3999),
                15, "Best Laptop.", true ,categoryRepository.findByName("Laptops").get());

        Product macBookAir = new Product(2L, "Mac Book Air 19'", new BigDecimal(2050),
                10, "Good Laptop.", true, categoryRepository.findByName("Laptops").get());

        Product IphoneX = new Product(3L, "Iphone X", new BigDecimal(5000),
                8, "Newest Iphone release.", true,categoryRepository.findByName("Smartphones").get());

        Product SamsungS8 = new Product(4L, "Samsung s8", new BigDecimal(4200),
                10, "Quite good phone.", true,categoryRepository.findByName("Smartphones").get());

        Product CanonPro = new Product(5L, "Canon Pro F215", new BigDecimal(1999),
                5, "This camera makes best pictures!", true,categoryRepository.findByName("Cameras").get());

        Product NikonFl = new Product(6L, "Nikon FL7", new BigDecimal(1500),
                12, "This camera makes best pictures!", true,categoryRepository.findByName("Cameras").get());

        Product CanonVw = new Product(7L, "Canon VW-921", new BigDecimal(1950),
                10, "Quite good camera.", true,categoryRepository.findByName("Cameras").get());

        Product MacBookAir2 = new Product(8L, "Mac Book Air 21'", new BigDecimal(4999),
                15, "Amazing Laptop.", true,categoryRepository.findByName("Laptops").get());

        Product Lenovo440 = new Product(9L, "Lenovo L440", new BigDecimal(2999),
                15, "Good for developing.", true,categoryRepository.findByName("Laptops").get());


        productRepository.saveAll(Arrays.asList(predator,macBookAir,IphoneX, SamsungS8,
                CanonPro, NikonFl, CanonVw, MacBookAir2, Lenovo440));
    }
}
