package pu.fmi.rent_a_car.common.exception;

import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import jakarta.persistence.EntityNotFoundException;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ErrorResponse handleEntityNotFound(EntityNotFoundException ex) {
    return new ErrorResponse(ex.getMessage(), 404);
  }

  @ExceptionHandler(DataValidationException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleDataValidation(DataValidationException ex) {
    return new ErrorResponse(ex.getMessage(), 404);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleConstraintViolation(
      MethodArgumentNotValidException ex) {

    var exceptions =
        ex.getBindingResult().getFieldErrors().stream()
            .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
    return ResponseEntity.status(BAD_REQUEST).body(exceptions);
  }
}
