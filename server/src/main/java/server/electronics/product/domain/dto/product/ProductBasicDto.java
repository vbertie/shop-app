package server.electronics.product.domain.dto.product;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class ProductBasicDto implements Serializable{

    private final Long id;
    private final String name;
    private final String type;
    private final BigDecimal price;
    private final int inStockNumber;
    private final String description;
    private final Boolean active;

    public ProductBasicDto(){
        this.id = 0l;
        this.name = null;
        this.type = null;
        this.price = new BigDecimal(0);
        this.inStockNumber = 0;
        this.description = null;
        this.active = null;
    }
}
