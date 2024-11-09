package pu.fmi.rent_a_car.offer.api;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pu.fmi.rent_a_car.offer.model.Offer;
import pu.fmi.rent_a_car.offer.model.OfferCreateRequest;
import pu.fmi.rent_a_car.offer.service.OfferService;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OfferApi {

  OfferService offerService;

  @PostMapping
  @ResponseStatus(CREATED)
  public Offer createOffer(@Valid @RequestBody OfferCreateRequest request) {
    return offerService.create(request);
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public Offer getOfferById(@PathVariable Long id) {
    return offerService.getById(id);
  }

  @GetMapping
  @ResponseStatus(OK)
  public Page<Offer> getOffersByLocation(Long userId, Pageable pageable) {
    return offerService.getByUserId(userId, pageable);
  }

  @PatchMapping("/{id}/accept")
  @ResponseStatus(OK)
  public Offer acceptOffer(@PathVariable Long id) {
    return offerService.acceptOffer(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
    offerService.delete(id);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
