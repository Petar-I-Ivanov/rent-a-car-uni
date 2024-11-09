package pu.fmi.rent_a_car.user.model;

import static lombok.AccessLevel.PRIVATE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class User {

  @Id @GeneratedValue Long id;

  @Column(length = 60, nullable = false)
  String firstName;

  @Column(length = 60, nullable = false)
  String lastName;

  @Column(length = 255, nullable = false)
  String address;

  @Column(length = 30, nullable = false, unique = true)
  String phone;

  @Column(nullable = false)
  Short age;

  @Column(nullable = false)
  boolean hasCarAccidents;

  @Column(nullable = false)
  boolean active;
}
