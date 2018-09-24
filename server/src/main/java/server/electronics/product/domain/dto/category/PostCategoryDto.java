package server.electronics.product.domain.dto.category;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class PostCategoryDto implements Serializable {

    @Null
    private final Long id;

    @Size(min = 1, max = 20)
    @NotNull
    private final String name;

    public PostCategoryDto(){
        this.id = null;
        this.name = "";
    }
}
