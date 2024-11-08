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
import pu.fmi.rent_a_car.office.model.OfficeRepository;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CarServiceImpl implements CarService {

  CarRepository carRepository;
  OfficeRepository officeRepository;

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
    var startMatchLocation = Optional.ofNullable(location).map(l -> l.concat("%")).orElse(null);
    return carRepository.findAllByOfficeCityLikeIgnoreCase(startMatchLocation, pageable);
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

  private Car requestIntoEntity(CarCreateEditRequest request, Car car) {
    return Optional.ofNullable(car)
        .map(Car::toBuilder)
        .orElseGet(Car::builder)
        .brand(request.brand())
        .model(request.model())
        .office(officeRepository.getReferenceById(request.officeId()))
        .pricePerDay(request.pricePerDay())
        .active(true)
        .taken(false)
        .build();
  }
}
