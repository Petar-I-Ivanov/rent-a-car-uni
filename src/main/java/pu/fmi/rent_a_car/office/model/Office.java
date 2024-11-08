package pu.fmi.rent_a_car.office.model;

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
public class Office {

  @Id @GeneratedValue Long id;

  @Column(length = 50, nullable = false)
  String country;

  @Column(length = 50, nullable = false)
  String city;

  @Column(length = 255, nullable = false)
  String address;
}
