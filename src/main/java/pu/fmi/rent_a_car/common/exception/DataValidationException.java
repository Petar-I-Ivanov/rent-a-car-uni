package pu.fmi.rent_a_car.common.exception;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DataValidationException extends RuntimeException {

  private static final long serialVersionUID = 6_174_696_771_324_496_825L;

  String message;
}
