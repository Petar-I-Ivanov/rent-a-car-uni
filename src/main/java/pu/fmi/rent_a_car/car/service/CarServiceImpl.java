package pu.fmi.rent_a_car.car.service;

import static lombok.AccessLevel.PRIVATE;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pu.fmi.rent_a_car.car.model.Car;
import pu.fmi.rent_a_car.car.model.CarCreateEditRequest;
import pu.fmi.rent_a_car.car.model.CarRepository;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CarServiceImpl implements CarService {

  CarRepository carRepository;

  @Override
  public Car create(CarCreateEditRequest request) {
    return carRepository.save(requestIntoEntity(request, null));
  }

  @Override
  public Car getById(Long id) {
    return carRepository.getReferenceById(id);
  }

  @Override
  public Page<Car> getAllByLocation(String location, Pageable pageable) {
    var lowerLocation = Optional.ofNullable(location).map(String::toLowerCase).orElse(null);
    return carRepository.findAllByLocationLikeIgnoreCase(lowerLocation, pageable);
  }

  @Override
  public Car update(Long id, CarCreateEditRequest request) {
    var car = requestIntoEntity(request, getById(id));
    return carRepository.save(car);
  }

  @Override
  public void delete(Long id) {
    var car = getById(id);
    car.setActive(false);
  }

  private static Car requestIntoEntity(CarCreateEditRequest request, Car car) {
    return Optional.ofNullable(car)
        .map(Car::toBuilder)
        .orElseGet(Car::builder)
        .brand(request.brand())
        .model(request.model())
        .location(request.location())
        .pricePerDay(request.pricePerDay())
        .active(true)
        .taken(false)
        .build();
  }
}
