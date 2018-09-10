package server.electronics.product.domain.exception.product;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ProductExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ProductExceptionDto> processValidationProduct(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();

        return Collections.unmodifiableList(bindingResult.getFieldErrors())
                .stream()
                .map(fieldError -> new ProductExceptionDto(fieldError.getField(), fieldError.getCode(), fieldError.getRejectedValue()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResultException.class)
    public ProductExceptionDto processProductNotFound(NoResultException ex){
        String message = ex.getMessage();
        return new ProductExceptionDto("ID", "cannot find", message.substring(message.indexOf(':')));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void processUnhandledError(Exception ex){
        log.warn(ex.getMessage());
        ex.printStackTrace();
    }

}
