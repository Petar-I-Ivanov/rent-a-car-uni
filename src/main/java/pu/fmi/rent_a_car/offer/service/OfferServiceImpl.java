package pu.fmi.rent_a_car.offer.service;

import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PRIVATE;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pu.fmi.rent_a_car.car.model.Car;
import pu.fmi.rent_a_car.car.service.CarService;
import pu.fmi.rent_a_car.common.exception.DataValidationException;
import pu.fmi.rent_a_car.offer.model.Offer;
import pu.fmi.rent_a_car.offer.model.OfferCreateRequest;
import pu.fmi.rent_a_car.offer.model.OfferRepository;
import pu.fmi.rent_a_car.user.model.User;
import pu.fmi.rent_a_car.user.model.UserRepository;
import pu.fmi.rent_a_car.utils.DateUtils;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OfferServiceImpl implements OfferService {

  UserRepository userRepository;
  OfferRepository offerRepository;
  CarService carService;

  @Override
  public Offer create(OfferCreateRequest request) {
    var car = carService.getById(request.carId());
    var user = userRepository.getReferenceById(request.userId());
    validateCarForOffer(car, user);

    var offer =
        Offer.builder()
            .car(car)
            .user(user)
            .daysOfRent(request.daysOfRent())
            .totalPrice(
                calcTotalPrice(
                    car.getPricePerDay(), user.isHasCarAccidents(), request.daysOfRent()))
            .active(true)
            .accepted(false)
            .expired(false)
            .build();

    return offerRepository.save(offer);
  }

  @Override
  public Offer getById(Long id) {
    return offerRepository.getReferenceById(id);
  }

  @Override
  public Page<Offer> getByUserId(Long userId, Pageable pageable) {
    return offerRepository.findAllByUserId(userId, pageable);
  }

  @Override
  public Offer acceptOffer(Long id) {
    var offer = getById(id);
    offer.setAccepted(true);
    return offer;
  }

  @Override
  public void delete(Long id) {
    var offer = getById(id);
    offer.setActive(false);
  }

  private static void validateCarForOffer(Car car, User user) {
    if (!car.isActive()) {
      throw new DataValidationException("Car must be active for offer!");
    }

    if (car.isTaken()) {
      throw new DataValidationException("Car must be free (not in active offer) for offer!");
    }

    if (!car.getOffice().getCity().equalsIgnoreCase(user.getAddress())) {
      throw new DataValidationException("Car's location must be same as user's location!");
    }
  }

  private static BigDecimal calcTotalPrice(
      BigDecimal carPrice, boolean hasUserAccidents, Short days) {
    var today = LocalDate.now();

    var totalPrice =
        IntStream.range(0, days)
            .mapToObj(today::plusDays)
            .map(DateUtils::isWeekend)
            .map(isWeekend -> isWeekend ? 1.10 : 1.0)
            .map(BigDecimal::valueOf)
            .map(carPrice::multiply)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    if (hasUserAccidents) {
      totalPrice = totalPrice.add(BigDecimal.valueOf(200));
    }

    return totalPrice.setScale(2, HALF_UP);
  }
}
