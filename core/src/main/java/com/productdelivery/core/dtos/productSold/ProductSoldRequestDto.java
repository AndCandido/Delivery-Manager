package com.productdelivery.core.dtos.productSold;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductSoldRequestDto(

    @NotBlank(message = "{productSold.ref.not-blank}")
    String ref,

    @NotBlank(message = "{productSold.name.not-blank}")
    String name,

    @NotNull(message = "{productSold.quantitySold.not-null}")
    @Positive(message = "{productSold.quantitySold.positive}")
    Integer quantitySold
) {
}
