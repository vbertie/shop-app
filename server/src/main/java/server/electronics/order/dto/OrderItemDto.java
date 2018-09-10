package server.electronics.order.dto;

import lombok.*;
import server.electronics.product.domain.dto.product.ProductDto;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemDto implements Serializable {

    private final Integer quantity;
    private final Long productId;
    private final ProductDto product;

    public OrderItemDto(){
        this.quantity = 0;
        this.productId = null;
        this.product = null;
    }


}
