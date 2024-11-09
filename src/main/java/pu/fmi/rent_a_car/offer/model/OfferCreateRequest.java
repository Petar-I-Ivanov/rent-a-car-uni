package pu.fmi.rent_a_car.offer.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OfferCreateRequest(
    @NotNull Long userId, @NotNull Long carId, @NotNull @Positive Short daysOfRent) {}
