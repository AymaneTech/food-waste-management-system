package net.foodeals.user.infrastructure.interfaces.web.advice;

import net.foodeals.common.models.ErrorResponse;
import net.foodeals.user.domain.exceptions.AuthorityNotFoundException;
import net.foodeals.user.domain.exceptions.RoleNotFoundException;
import net.foodeals.user.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class UserDomainAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        final ErrorResponse errorReponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                Map.of()
        );
        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException e) {
        final ErrorResponse errorReponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                Map.of()
        );
        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthorityNotFoundException(AuthorityNotFoundException e) {
        final ErrorResponse errorReponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                Map.of()
        );
        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }


}
