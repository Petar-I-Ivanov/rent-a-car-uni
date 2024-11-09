package pu.fmi.rent_a_car.user.api;

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
import pu.fmi.rent_a_car.user.model.User;
import pu.fmi.rent_a_car.user.model.UserCreateEditRequest;
import pu.fmi.rent_a_car.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserApi {

  UserService userService;

  @PostMapping
  @ResponseStatus(CREATED)
  public User createUser(@Valid @RequestBody UserCreateEditRequest request) {
    return userService.create(request);
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public User getUserById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @GetMapping
  @ResponseStatus(OK)
  public Page<User> getAllUsers(Pageable pageable) {
    return userService.getAll(pageable);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  public User updateUser(@PathVariable Long id, @Valid @RequestBody UserCreateEditRequest request) {
    return userService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
