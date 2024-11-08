package pu.fmi.rent_a_car.car.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CarCreateEditRequest(
    @NotBlank @Size(max = 50) String brand,
    @NotBlank @Size(max = 255) String model,
    @NotNull Location location,
    @Positive BigDecimal pricePerDay) {}
