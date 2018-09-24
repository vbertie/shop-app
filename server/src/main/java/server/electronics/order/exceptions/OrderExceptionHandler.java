package server.electronics.order.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@NoArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
class OrderExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderException.class)
    private OrderExceptionDto processOrderNotFoundException(OrderException ex){
        String message = ex.getMessage();
        return new OrderExceptionDto("ID", "cannot find", message.substring(message.indexOf(':')));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private List<OrderExceptionDto> processValidationOrder(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();

        return Collections.unmodifiableList(bindingResult.getFieldErrors())
                .stream()
                .map(error -> new OrderExceptionDto(error.getField(), error.getCode(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
}
