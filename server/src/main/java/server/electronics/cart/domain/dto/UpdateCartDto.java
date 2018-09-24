package server.electronics.cart.domain.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UpdateCartDto {

    @NotNull
    private final Long productId;

    @Min(1)
    private final int amount;
}
