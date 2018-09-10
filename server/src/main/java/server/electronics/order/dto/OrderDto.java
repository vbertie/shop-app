package server.electronics.order.dto;

import lombok.*;
import server.electronics.cart.domain.dto.CartDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDto implements Serializable {

    @Null
    private final Long id;

    @Valid
    @NotNull
    private final CustomerDto customer;

    @NotNull
    private final CartDto cart;

    private final Boolean isPaid;
    private final Boolean isDelivered;

    public OrderDto(){
        this.id = null;
        this.customer = new CustomerDto();
        this.cart = new CartDto();
        this.isPaid = null;
        this.isDelivered = null;
    }
}
