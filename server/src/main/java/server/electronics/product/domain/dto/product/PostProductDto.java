package server.electronics.product.domain.dto.product;

import lombok.*;
import javax.validation.constraints.*;

@Builder
@Getter
@AllArgsConstructor
public class PostProductDto{

    @Null
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private final String name;

    @NotNull
    private final String type;

    @NotNull
    @Pattern(regexp="([0-9]+([.][0-9]{1,2})?)")
    private final String price;

    @NotNull
    @Pattern(regexp="([0-9]+)")
    private final String inStockNumber;

    @Size(max = 255)
    private final String description ;

    private final Boolean active;

    private PostProductDto(){
        this.id = null;
        this.name = "";
        this.type = "";
        this.price = "";
        this.inStockNumber = "";
        this.description = null;
        this.active = null;
    }
}
