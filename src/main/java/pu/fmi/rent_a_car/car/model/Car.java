package pu.fmi.rent_a_car.car.model;

import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pu.fmi.rent_a_car.office.model.Office;

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

  @ManyToOne
  @JoinColumn(name = "office_id")
  Office office;

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
