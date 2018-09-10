package server.electronics.product.domain

import server.electronics.product.domain.dto.product.ProductDto
import server.electronics.product.domain.dto.promotion.PostPromotionDto
import server.electronics.product.domain.dto.promotion.PromotionDto
import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream

class PromotionSpecTest extends Specification implements SampleProducts, SamplePromotions, SampleCategories{

    ProductFacade productFacade = new ProductConfiguration().productFacade()

    def setup(){
        productFacade.addCategory(smartphone)
        productFacade.addCategory(camera)
        productFacade.addCategory(laptop)
        productFacade.addProduct(acer)
        productFacade.addProduct(iphone)
    }

    def "Should add promotion to database" () {
        given: "We have promotion"
            PostPromotionDto postPromotionDto = PostPromotionDto.builder()
                    .id(1l)
                    .promotionName("Promotion -20%")
                    .percentageReduction("20")
                    .isActive(false)
                    .build()

        when: "We addProduct promotion to database"
            productFacade.addPromotion(postPromotionDto)

        then:"Promotion is added to database"
            productFacade.showPromotion(postPromotionDto.id).id == postPromotionDto.getId()
    }

    def "Should delete promotion from database" () {
        given: "We have 1 promotions in database, and product connected to it"
            productFacade.addPromotion(postPromotionDto)
            productFacade.findAllPromotions().size() == 1

            PromotionDto promotionDto = PromotionDto.builder()
                    .id(1l)
                    .promotionName("Promotion -20%")
                    .isActive(false)
                    .percentageReduction(20)
                    .products(Arrays.asList(iphoneShowDto))
                    .build()

            productFacade.addProductPromotion(promotionDto)

            ProductDto ip3 = productFacade.showProduct(iphoneShowDto.id)
            ip3.price == iphoneShowDto.price * 0.8

        when: "We want to delete one of promotions"
            productFacade.deletePromotion(postPromotionDto.id)
            ProductDto ipAfterPromotionDeleted = productFacade.showProduct(iphoneShowDto.id)

        then: "We have only one promotion in database"
            productFacade.findAllPromotions().size() == 0
            ipAfterPromotionDeleted.price == iphoneShowDto.price
    }

    def "Should return back promotion of given id" (){
        given: "We have one promotion in database, with products assigned to it"
            productFacade.addPromotion(postPromotionDto)
            productFacade.findAllPromotions().size() == 1

            PromotionDto promotionDto = PromotionDto.builder()
                    .id(1l)
                    .promotionName("Promotion -20%")
                    .isActive(false)
                    .percentageReduction(20)
                    .products(Arrays.asList(iphoneShowDto))
                    .build()

            productFacade.addProductPromotion(promotionDto)

        when: "We want to receive promotion with products assigned to it"
            PromotionDto receivedPromotion = productFacade.getProductPromotion(postPromotionDto.id)

        then: "Our promotionDto contain correct data"
            receivedPromotion.products.get(0).name == iphoneShowDto.name
            receivedPromotion.promotionName == postPromotionDto.promotionName
    }

    def "Should add products into promotion" () {
        given: "We have promotion and products in database"
            productFacade.addPromotion(postPromotionDto)

        when: "We want to addProduct promotion to products"
            PromotionDto promotionDto = PromotionDto.builder()
                    .id(1l)
                    .promotionName("Promotion -20%")
                    .isActive(false)
                    .percentageReduction(20)
                    .products(Arrays.asList(iphoneShowDto,acerShowDto))
                    .build()

            productFacade.addProductPromotion(promotionDto)

        then: "Our products exist on promotion (price reduction -20%)"
            ProductDto ip3 = productFacade.showProduct(iphoneShowDto.id)
            ProductDto ip4 = productFacade.showProduct(acerShowDto.id)

            ip3.price == iphoneShowDto.price * 0.8
            ip4.price == acerShowDto.price * 0.8
    }

    def "Should remove promotion from chosen products" () {
        given: "We have promotion on two products"
            productFacade.addPromotion(postPromotionDto)
            PromotionDto promotionDto = PromotionDto.builder()
                    .id(1l)
                    .promotionName("Promotion -20%")
                    .isActive(false)
                    .percentageReduction(10)
                    .products(Arrays.asList(iphoneShowDto,acerShowDto))
                    .build()

            productFacade.addProductPromotion(promotionDto)

            ProductDto ip3 = productFacade.showProduct(iphoneShowDto.id)
            ProductDto ip4 = productFacade.showProduct(acerShowDto.id)

            ip3.price == iphoneShowDto.price * 0.8
            ip4.price == acerShowDto.price * 0.8

        when: "We want to delete promotions from chosen products"
            productFacade.deleteProductPromotion(promotionDto)

        then: "Products are not at promotion"
            ProductDto ip3deleted = productFacade.showProduct(iphoneShowDto.id)
            ProductDto ip4deleted = productFacade.showProduct(acerShowDto.id)

            ip3deleted.price == iphoneShowDto.price
            ip4deleted.price == acerShowDto.price
    }
}
