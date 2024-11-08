package pu.fmi.rent_a_car.car.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pu.fmi.rent_a_car.car.model.Car;
import pu.fmi.rent_a_car.car.model.CarCreateEditRequest;

public interface CarService {

  Car create(CarCreateEditRequest request);

  Car getById(Long id);

  Page<Car> getAllByLocation(String location, Pageable pageable);

  Car update(Long id, CarCreateEditRequest request);

  void delete(Long id);
}
