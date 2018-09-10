package server.electronics.order.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ShowOrderDto implements Serializable {

    private final Long id;
    private final Boolean isPaid;
    private final Boolean isDelivered;
    private final CustomerDto customer;
    private final Set<OrderItemDto> items;
    private final BigDecimal grandTotal;

    public ShowOrderDto(){
        this.id = null;
        this.isDelivered = null;
        this.isPaid = null;
        this.customer = null;
        this.items = null;
        this.grandTotal = new BigDecimal(0);
    }
}