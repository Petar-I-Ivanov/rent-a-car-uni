package pu.fmi.rent_a_car.car.model;

import static jakarta.persistence.EnumType.STRING;
import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = PRIVATE)
public class Car {

  @Id @GeneratedValue Long id;

  @Column(length = 50, nullable = false)
  String brand;

  @Column(length = 255, nullable = false)
  String model;

  @Column(length = 50, nullable = false)
  @Enumerated(STRING)
  Location location;

  @Column(scale = 2, nullable = false)
  BigDecimal pricePerDay;

  @Column(nullable = false)
  boolean taken;

  @Column(nullable = false)
  boolean active;

  public void setPricePerDay(BigDecimal pricePerDay) {
    this.pricePerDay = pricePerDay.setScale(2, HALF_UP);
  }
}
