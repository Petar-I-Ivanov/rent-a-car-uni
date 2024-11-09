package pu.fmi.rent_a_car.offer.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

  Page<Offer> findAllByUserId(Long userId, Pageable pageable);
}
