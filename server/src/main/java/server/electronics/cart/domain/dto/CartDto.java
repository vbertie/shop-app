package server.electronics.cart.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Wither;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Wither
@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CartDto implements Serializable {

    @JsonIgnore
    private String cartId;
    private Map<Long, CartItemDto> cartItems;
    private BigDecimal grandTotal;

    public CartDto(String cartId){
        this.cartId = cartId;
        this.cartItems = new HashMap<>();
        this.grandTotal = new BigDecimal(0);
    }
}

