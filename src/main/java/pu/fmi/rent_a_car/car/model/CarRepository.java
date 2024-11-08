package pu.fmi.rent_a_car.car.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  Page<Car> findAllByOfficeCityLikeIgnoreCase(String location, Pageable pageable);
}
