package server.electronics.product.domain;

import server.electronics.product.domain.dto.promotion.PostPromotionDto;
import server.electronics.product.domain.dto.promotion.PromotionDto;

interface PromotionService {

     Product addProductPromotion(Product product, Promotion promotion);

     Product deleteProductPromotion(Product product, Promotion promotion);

     Promotion convert(PostPromotionDto dto);

    PromotionDto convertToProductDto(Promotion promotion);
}
