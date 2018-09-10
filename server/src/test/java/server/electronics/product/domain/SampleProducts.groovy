package server.electronics.product.domain

import server.electronics.product.domain.dto.product.PostProductDto
import server.electronics.product.domain.dto.product.ProductBasicDto
import server.electronics.product.domain.dto.product.ProductDto

trait SampleProducts {
    PostProductDto acer = createPostProductDto(1l, "Acer Predator", "Laptops", "2525","15" ,"Super Laptop")
    PostProductDto iphone = createPostProductDto(2l, "Iphone X", "Smartphones", "2555","5", "Super Phone")
    PostProductDto canon = createPostProductDto(3l, "Canon Pro", "Cameras", "3210","10", "Super Camera")
    PostProductDto iphone2 = createPostProductDto(4l, "iphone", "Smartphones", "2222","5", "Super Phone")
    ProductDto iphoneShowDto = createProductBasicDto(2l, "Iphone X", "Smartphones", new BigDecimal(2555),5, "Super Phone")
    ProductDto acerShowDto = createProductBasicDto(1l, "Acer Predator", "Laptops", new BigDecimal(2525),15 ,"Super Laptop")
    ProductDto iphone2ShowDto = createProductBasicDto(4l, "iphone", "Smartphones", new BigDecimal(2222),5, "Super Phone")

    static private PostProductDto createPostProductDto(Long id, String name, String type, String price, String inStockNumber, String description){
        return PostProductDto.builder()
                .id(id)
                .name(name)
                .type(type)
                .price(price)
                .description(description)
                .inStockNumber(inStockNumber)
                .build()
    }

    static private ProductDto createProductBasicDto(Long id, String name, String type, BigDecimal price, int inStockNumber, String description){
        return ProductDto.builder()
                .id(id)
                .name(name)
                .type(type)
                .price(price)
                .description(description)
                .inStockNumber(inStockNumber)
                .build()
    }
}