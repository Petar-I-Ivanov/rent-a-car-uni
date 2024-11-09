package pu.fmi.rent_a_car.user.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByPhone(String phone);

  boolean existsByPhoneAndIdNot(String phone, Long id);
}
