package server.electronics.cart.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Wither;
import server.electronics.product.domain.dto.product.ProductDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Wither
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class CartItemDto implements Serializable {

    @NotNull
    private final ProductDto product;

    @Min(1)
    private final int quantity;

    private final BigDecimal totalPrice;

    private CartItemDto(){
        this.product = new ProductDto();
        this.quantity = 0;
        this.totalPrice = new BigDecimal(0);
    }
}
