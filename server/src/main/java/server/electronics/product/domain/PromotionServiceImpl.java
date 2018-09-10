package server.electronics.product.domain;

import server.electronics.product.domain.dto.promotion.PostPromotionDto;
import server.electronics.product.domain.dto.promotion.PromotionDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static server.electronics.util.mapper.SuperConverter.setIfNonNull;

class PromotionServiceImpl implements PromotionService {

    @Override
    public Product addProductPromotion(Product product, Promotion promotion){
        product.setPromotion(promotion);
        product.setOldPrice(product.getPrice());
        product.setPrice(product.getPrice()
                .multiply(BigDecimal.valueOf((100 - promotion.getPercentageReduction()) * 0.01))
                .setScale(1, RoundingMode.HALF_DOWN));

        promotion.getProducts().add(product);

        return product;
    }

    @Override
    public Product deleteProductPromotion(Product product, Promotion promotion){
        product.setPromotion(null);
        product.setPrice(product.getOldPrice());
        promotion.getProducts().remove(product);

        return product;
    }

    @Override
    public Promotion convert(PostPromotionDto dto){
        return update(dto, new Promotion());
    }

    @Override
    public PromotionDto convertToProductDto(Promotion promotion){
        return getPromotionDto(promotion);
    }

    private Promotion update(PostPromotionDto updater, Promotion result){
        setIfNonNull(updater.getId(), result::setId);
        setIfNonNull(Integer.valueOf(updater.getPercentageReduction()), result::setPercentageReduction);
        setIfNonNull(updater.getPromotionName(), result::setPromotionName);

        return result;
    }

    private PromotionDto getPromotionDto(Promotion promotion){
       return PromotionDto.builder()
               .id(promotion.getId())
               .percentageReduction(promotion.getPercentageReduction())
               .promotionName(promotion.getPromotionName())
               .products(promotion.getProducts()
                       .stream()
                       .map(Product::dto)
                       .collect(Collectors.toList()))
               .build();
    }

    private static <E> void setIfNonNull(E value, Consumer<E> setterMethod){
        if(value != null)
            setterMethod.accept(value);
    }
}
