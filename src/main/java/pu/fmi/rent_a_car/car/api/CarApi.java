package pu.fmi.rent_a_car.car.api;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pu.fmi.rent_a_car.car.model.Car;
import pu.fmi.rent_a_car.car.model.CarCreateEditRequest;
import pu.fmi.rent_a_car.car.service.CarService;

@RestController
@RequestMapping("/api/v1/car")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CarApi {

  CarService carService;

  @PostMapping
  @ResponseStatus(CREATED)
  public Car createCar(@Valid @RequestBody CarCreateEditRequest request) {
    return carService.create(request);
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public Car getCarById(@PathVariable Long id) {
    return carService.getById(id);
  }

  @GetMapping
  @ResponseStatus(OK)
  public Page<Car> getCarsByLocation(String location, Pageable pageable) {
    return carService.getAllByLocation(location, pageable);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  public Car updateCar(@PathVariable Long id, @Valid @RequestBody CarCreateEditRequest request) {
    return carService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
    carService.delete(id);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
