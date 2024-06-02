package com.productdelivery.core.dtos.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record EmployeeRequestDto(

    @NotBlank(message = "{employee.name.not-blank}")
    String name,

    @NotBlank(message = "{employee.registryNumber.not-blank}")
    String registryNumber
) {
}
