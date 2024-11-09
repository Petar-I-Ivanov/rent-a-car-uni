package pu.fmi.rent_a_car.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pu.fmi.rent_a_car.user.model.User;
import pu.fmi.rent_a_car.user.model.UserCreateEditRequest;

public interface UserService {

  User create(UserCreateEditRequest request);

  User getById(Long id);

  Page<User> getAll(Pageable pageable);

  User update(Long id, UserCreateEditRequest request);

  void delete(Long id);
}
