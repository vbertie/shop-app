package server.electronics.order.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
class OrderExceptionDto implements Serializable {

    private String field;
    private String code;
    private Object rejectedValue;
}
