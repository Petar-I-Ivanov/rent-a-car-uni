package pu.fmi.rent_a_car.user.service;

import static lombok.AccessLevel.PRIVATE;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pu.fmi.rent_a_car.common.exception.DataValidationException;
import pu.fmi.rent_a_car.user.model.User;
import pu.fmi.rent_a_car.user.model.UserCreateEditRequest;
import pu.fmi.rent_a_car.user.model.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

  UserRepository userRepository;

  @Override
  public User create(UserCreateEditRequest request) {
    validateExistingPhone(request.phone(), null);
    return userRepository.save(requestIntoEntity(request, null));
  }

  @Override
  public User getById(Long id) {
    return userRepository.getReferenceById(id);
  }

  @Override
  public Page<User> getAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public User update(Long id, UserCreateEditRequest request) {
    validateExistingPhone(request.phone(), id);
    var user = requestIntoEntity(request, getById(id));
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    var user = getById(id);
    user.setActive(false);
  }

  private void validateExistingPhone(String phone, Long exceptId) {
    var isExisting =
        Optional.ofNullable(exceptId)
            .map(id -> userRepository.existsByPhoneAndIdNot(phone, id))
            .orElseGet(() -> userRepository.existsByPhone(phone));

    if (isExisting) {
      throw new DataValidationException("User with given phone already exists!");
    }
  }

  private static User requestIntoEntity(UserCreateEditRequest request, User user) {
    return Optional.ofNullable(user)
        .map(User::toBuilder)
        .orElseGet(User::builder)
        .firstName(request.firstName())
        .lastName(request.lastName())
        .address(request.address())
        .phone(request.phone())
        .age(request.age())
        .hasCarAccidents(request.hasCarAccidents())
        .build();
  }
}
