package net.foodeals.product.infrastructure.interfaces.web.advice;

import net.foodeals.common.models.ErrorResponse;
import net.foodeals.product.domain.exceptions.ProductCategoryNotFoundException;
import net.foodeals.product.domain.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ProductDomainAdvice {

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductCategoryNotFoundException(ProductCategoryNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                Map.of()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                Map.of()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}