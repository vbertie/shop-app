package server.electronics.product.domain.exception.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
class ProductExceptionDto implements Serializable {
    private final String field;
    private final String code;
    private final Object rejectedValue;
}
