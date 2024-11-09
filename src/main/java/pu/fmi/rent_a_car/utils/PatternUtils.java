package pu.fmi.rent_a_car.utils;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class PatternUtils {

  public static final String CAPITALIZED_NAME = "^[A-Z][a-z]*$";
  public static final String PHONE_NUMBER = "^\\d{7,15}$";
}
