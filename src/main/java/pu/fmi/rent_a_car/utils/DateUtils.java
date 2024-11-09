package pu.fmi.rent_a_car.utils;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class DateUtils {

  public static boolean isWeekend(LocalDate date) {
    var dayOfWeek = date.getDayOfWeek();
    return dayOfWeek == SATURDAY || dayOfWeek == SUNDAY;
  }
}
