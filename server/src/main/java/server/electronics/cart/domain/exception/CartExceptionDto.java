package server.electronics.cart.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
class CartExceptionDto implements Serializable {

    private final String message;
}
