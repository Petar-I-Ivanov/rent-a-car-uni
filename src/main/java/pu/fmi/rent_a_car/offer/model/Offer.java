package pu.fmi.rent_a_car.offer.model;

import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import pu.fmi.rent_a_car.car.model.Car;
import pu.fmi.rent_a_car.user.model.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = PRIVATE)
public class Offer {

  @Id @GeneratedValue Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "car_id", nullable = false)
  Car car;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column(nullable = false)
  Short daysOfRent;

  @Column(scale = 2, nullable = false)
  BigDecimal totalPrice;

  LocalDate acceptedAt;

  @Column(nullable = false)
  boolean accepted;

  LocalDate expireAt;

  @Column(nullable = false)
  boolean expired;

  @Column(nullable = false)
  boolean active;
}
