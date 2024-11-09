package pu.fmi.rent_a_car.user.model;

import static pu.fmi.rent_a_car.utils.PatternUtils.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UserCreateEditRequest(
    @NotBlank @Size(max = 60) @Pattern(regexp = CAPITALIZED_NAME) String firstName,
    @NotBlank @Size(max = 60) @Pattern(regexp = CAPITALIZED_NAME) String lastName,
    @NotBlank @Size(max = 255) String address,
    @NotBlank @Size(max = 30) @Pattern(regexp = PHONE_NUMBER) String phone,
    @NotNull @Positive @Min(value = 18) Short age,
    @NotNull Boolean hasCarAccidents) {}
