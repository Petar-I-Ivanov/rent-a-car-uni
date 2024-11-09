package pu.fmi.rent_a_car.offer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pu.fmi.rent_a_car.offer.model.Offer;
import pu.fmi.rent_a_car.offer.model.OfferCreateRequest;

public interface OfferService {

  Offer create(OfferCreateRequest request);

  Offer getById(Long id);

  Page<Offer> getByUserId(Long userId, Pageable pageable);

  Offer acceptOffer(Long id);

  void delete(Long id);
}
