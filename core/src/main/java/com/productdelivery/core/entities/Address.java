package com.productdelivery.core.entities;

import jakarta.validation.constraints.NotBlank;

public record Address (

    @NotBlank(message = "{address.city.not-blank}")
    String city,

    @NotBlank(message = "{address.state.not-blank}")
    String state,

    @NotBlank(message = "{address.road.not-blank}")
    String road,

    @NotBlank(message = "{address.houseNumber.not-blank}")
    String houseNumber
) {
}