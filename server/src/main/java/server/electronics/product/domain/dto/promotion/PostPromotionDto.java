package server.electronics.product.domain.dto.promotion;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class PostPromotionDto implements Serializable {

    @Null
    private final Long id;

    private Long productId;

    @NotNull
    @Size(min = 1, max = 40)
    private String promotionName;

    @NotNull
    @Max(100)
    @Pattern(regexp="([0-9]+)")
    private String percentageReduction;

    private Boolean isActive;

    public PostPromotionDto(){
        this.id = null;
        this.productId = null;
        this.promotionName = "";
        this.percentageReduction = "";
        this.isActive = null;
    }
}
