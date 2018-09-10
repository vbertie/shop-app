package server.electronics.order.domain;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Immutable
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class OrderItem {

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private int quantity;
}
