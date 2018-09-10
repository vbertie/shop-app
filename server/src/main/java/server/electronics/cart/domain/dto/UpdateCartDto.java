package server.electronics.cart.domain.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class UpdateCartDto {

    @NotNull
    private final Long productId;

    @Min(1)
    private final int amount;

    public UpdateCartDto(){
        this.productId = null;
        this.amount = 0;
    }
}
