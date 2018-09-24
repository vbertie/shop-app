package server.electronics.product.domain.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
public class UpdateCategoryDto implements Serializable {

    @NotNull
    private final Long id;

    @NotNull
    @Size(min = 1, max = 20)
    private final String name;

    public UpdateCategoryDto(){
        this.id = 1232323231l;
        this.name = "";
    }
}
