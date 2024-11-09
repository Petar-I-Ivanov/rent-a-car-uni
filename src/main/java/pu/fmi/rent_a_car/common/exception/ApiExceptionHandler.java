package pu.fmi.rent_a_car.common.exception;

import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import org.springframework.http.ResponseEntity;
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

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleConstraintViolation(
      ConstraintViolationException ex) {
    var exceptions =
        ex.getConstraintViolations().stream()
            .collect(toMap(ApiExceptionHandler::getParameterName, ConstraintViolation::getMessage));
    return ResponseEntity.status(BAD_REQUEST).body(exceptions);
  }

  private static String getParameterName(ConstraintViolation<?> violation) {
    var pathParts = violation.getPropertyPath().toString().split("\\.");
    return pathParts[pathParts.length - 1];
  }
}
