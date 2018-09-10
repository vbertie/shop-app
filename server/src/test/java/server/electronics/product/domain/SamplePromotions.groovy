package server.electronics.product.domain

import server.electronics.product.domain.dto.product.ProductDto
import server.electronics.product.domain.dto.promotion.PostPromotionDto
import server.electronics.product.domain.dto.promotion.PromotionDto

import java.util.stream.Collectors
import java.util.stream.Stream

trait SamplePromotions implements SampleProducts{
    PostPromotionDto postPromotionDto = createPostPromotionDto(1l,"Promotion -20%", false, "20");
    PostPromotionDto postPromotionDto2 = createPostPromotionDto(2l,"Promotion -10%", false, "10");
    PromotionDto promotionDto1 = createPromotionDto(1l, "Promotion -20%", false, 20)
    PromotionDto promotionDto2 = createPromotionDto(2l,"Promotion -10%", false, 10)

    static private PostPromotionDto createPostPromotionDto(Long id, String name, Boolean active, String percentage){
        return PostPromotionDto.builder()
                    .id(id)
                    .percentageReduction(percentage)
                    .isActive(active)
                    .promotionName(name)
                    .build()
    }

    static private PromotionDto createPromotionDto(Long id, String name, Boolean active, Integer percentage){
        return PromotionDto.builder()
                .id(id)
                .isActive(active)
                .promotionName(name)
                .percentageReduction(percentage)
                .build()
    }
}

