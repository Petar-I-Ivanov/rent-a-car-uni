package pu.fmi.rent_a_car.car.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  @Query(value = "select c from Car c where lower(c.location) like %:location%")
  Page<Car> findAllByLocationLikeIgnoreCase(@Param("location") String location, Pageable pageable);
}
