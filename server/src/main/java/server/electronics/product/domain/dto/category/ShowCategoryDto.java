package server.electronics.product.domain.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import server.electronics.product.domain.dto.product.ProductDto;

import java.io.Serializable;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class ShowCategoryDto implements Serializable {

    private final Long id;

    private final String name;

    private final Set<ProductDto> products;

    public ShowCategoryDto(){
        this.id = null;
        this.name = null;
        this.products = null;
    }
}
