package server.electronics.product.domain.dto.product;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
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

    public ProductDto(){
        this.id = 0l;
        this.promotionId = 0l;
        this.name = null;
        this.type = null;
        this.price = new BigDecimal(0);
        this.oldPrice = new BigDecimal(0);
        this.inStockNumber = 0;
        this.description = null;
        this.active = null;
    }
}


