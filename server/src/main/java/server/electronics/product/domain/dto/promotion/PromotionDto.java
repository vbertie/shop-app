package server.electronics.product.domain.dto.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import server.electronics.product.domain.dto.product.ProductDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class PromotionDto implements Serializable {

    private final Long id;

    private final Boolean isActive;

    private final String promotionName;

    private final Integer percentageReduction;

    private List<ProductDto> products;

    public PromotionDto(){
        this.id = null;
        this.isActive = null;
        this.promotionName = null;
        this.percentageReduction = null;
        this.products = new ArrayList<>();
    }
}
