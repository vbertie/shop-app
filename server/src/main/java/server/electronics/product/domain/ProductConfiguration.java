package server.electronics.product.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.electronics.product.domain.dto.product.PostProductDto;
import server.electronics.product.domain.dto.product.ProductDto;
import server.electronics.util.mapper.SuperConverter;

@Configuration
class ProductConfiguration {

    ProductFacade productFacade(){
        return productFacade(new InMemoryProductRepository(), new InMemoryPromotionRepository(), new InMemoryCategoryRepository());
    }

    @Bean
    ProductFacade productFacade(ProductRepository productRepository, PromotionRepository promotionRepository,
                                CategoryRepository categoryRepository){
        SuperConverter<PostProductDto, ProductDto, Product> productConverter = new ProductConverter(categoryRepository);
        PromotionService promotionService = new PromotionServiceImpl();
        ImageUploader imageUploader = new ImageUploader();
        return new ProductFacade(productRepository, promotionRepository, productConverter,promotionService, imageUploader, categoryRepository);
    }

    @Bean
    public ProductAndCategoryInitializer productAndCategoryInitializer(CategoryRepository categoryRepository, ProductRepository productRepository){
        return new ProductAndCategoryInitializer(productRepository, categoryRepository);
    }
}
