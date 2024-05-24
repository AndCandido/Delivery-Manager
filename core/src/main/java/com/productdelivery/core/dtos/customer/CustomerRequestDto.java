package com.productdelivery.core.dtos.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDto(

    @NotBlank(message = "{customer.name.not-blank}")
    String name,

    @NotBlank(message = "{customer.email.not-blank}")
    @Email(message = "{customer.email.valid}")
    String email
) {
}
