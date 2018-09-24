package server.electronics.product.domain.dto.product;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ProductDto implements Serializable {

    private Long id;
    private Long promotionId;
    private String name;
    private String type;
    private BigDecimal price;
    private int inStockNumber;
    private String description;
    private Boolean active;
    private BigDecimal oldPrice;
}


