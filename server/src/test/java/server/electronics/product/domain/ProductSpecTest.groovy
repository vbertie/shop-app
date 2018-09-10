package server.electronics.product.domain

import server.electronics.product.domain.dto.product.PostProductDto
import server.electronics.product.domain.dto.product.ProductDto
import server.electronics.product.domain.dto.promotion.PromotionDto
import spock.lang.Specification

import javax.persistence.NoResultException

class ProductSpecTest extends Specification implements SampleProducts, SamplePromotions, SampleCategories{

    ProductFacade productFacade = new ProductConfiguration().productFacade()

    def setup(){
        productFacade.addCategory(smartphone)
        productFacade.addCategory(camera)
        productFacade.addCategory(laptop)
    }
    def "Should add product to database"() {

        when: "Product is added"
            productFacade.addProduct(iphone)
            List<ProductDto> products = productFacade.findAllProducts()

        then: "List should contain the product"
            products.size() == 1
            productFacade.showProduct(iphone.id) != null
    }

    def "Should get list of products"() {

        given: "We have two products in system"
            productFacade.addProduct(acer)
            productFacade.addProduct(iphone)

        when: "We ask for all products"
            List<ProductDto> products = productFacade.findAllProducts()

        then: "System returns us the products that we added"
            products.size() == 2
    }

    def "Should delete product from system"() {

        given: "We have one products in system"
        productFacade.addProduct(acer)

        when: "We delete given product"
            productFacade.deleteProduct(acer.id)

        then: "There are not any products"
            productFacade.findAllProducts().isEmpty()
    }

    def "Should show chosen product"() {

        given: "We have one product in system"
            PostProductDto productDto = PostProductDto.builder()
                    .id(1l)
                    .name("Acer Predator")
                    .type("Laptops")
                    .price("2999")
                    .description("Some description")
                    .inStockNumber("25")
                    .build()

            productFacade.addProduct(productDto)

        when: "We ask for chose product"
            ProductDto foundProduct = productFacade.showProduct(1l)

        then: "Found product id && name is equal to given product"
            foundProduct.id == productDto.id
            foundProduct.name == productDto.name
    }

    def "Should get list of products with chosen category" () {

        given:"We have name of category, and three products in system"
            String category = "smartphones"
            productFacade.addProduct(iphone)
            productFacade.addProduct(iphone2)
            productFacade.addProduct(canon)
            productFacade.addProduct(acer)

        when:"We ask for list of products"
            List<ProductDto> products = productFacade.findProductsByCategory(category)

        then:"We have list of products that are in category"
            products.size() == 2
    }

    def "Should get products that contains keyword" () {

        given: "We have products in database, and given keyword"
            productFacade.addProduct(acer)
            productFacade.addProduct(iphone)
            productFacade.addProduct(iphone2)
            productFacade.addProduct(canon)

            String keyword = "iphone"

        when: "We ask for product that contains keyword in name"
            List<ProductDto> products = productFacade.findProductsWithKeyword(keyword)

        then: "Our list contains 2 products"
            products.get(0).name == "Iphone X"
            products.get(1).name == "iphone"
            products.size() == 2
    }

    def "Should throw exception if Product not found" () {

        when: "We ask for product with id 1"
            productFacade.showProduct(1l)

        then: "Exception is thrown"
            thrown(NoResultException)
    }

    def "Should return list with products at promotion" () {

        given: "We have four products, and two of them is at promotion in database"
            productFacade.addProduct(iphone)
            productFacade.addProduct(acer)
            productFacade.addProduct(canon)
            productFacade.addProduct(iphone2)
            productFacade.addPromotion(postPromotionDto)

            PromotionDto promotionDto = PromotionDto.builder()
                    .id(1l)
                    .promotionName("Promotion -20%")
                    .isActive(false)
                    .percentageReduction(20)
                    .products(Arrays.asList(iphoneShowDto,acerShowDto))
                    .build()

            productFacade.addProductPromotion(promotionDto)

        when: "We want to receive products at promotions"
            List<ProductDto> productsAtPromotion = productFacade.findProductsAtPromotion();

        then: "We have 2 products inside list"
            productsAtPromotion.size() == 2
    }

    def "Should return list with products at promotion of given category" () {
        given: "We have four products, and two of them is at promotion in database"
        productFacade.addProduct(iphone)
        productFacade.addProduct(acer)
        productFacade.addProduct(canon)
        productFacade.addProduct(iphone2)
        productFacade.addPromotion(postPromotionDto)

        PromotionDto promotionDto = PromotionDto.builder()
                .id(1l)
                .promotionName("Promotion -20%")
                .isActive(false)
                .percentageReduction(20)
                .products(Arrays.asList(iphoneShowDto,acerShowDto,iphone2ShowDto))
                .build()

        productFacade.addProductPromotion(promotionDto)

        when: "We want to receive smartphones at promotion"
        List<ProductDto> smartphonesAtPromotion = productFacade.findProductsAtPromotionWithCategory("Smartphones");

        then: "We have 2 products inside list"
        smartphonesAtPromotion.size() == 2
    }
}