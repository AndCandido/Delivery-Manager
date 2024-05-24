package com.productdelivery.core.dtos.customer;

import com.productdelivery.core.entities.Customer;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerResponseDto(
    String id,
    String name,
    String email,
    LocalDateTime createdAt
) {

    public static CustomerResponseDto modelToDto(Customer customer) {
        return CustomerResponseDto.builder()
            .id(customer.getId())
            .name(customer.getName())
            .email(customer.getEmail())
            .createdAt(customer.getCreatedAt())
            .build();
    }
}
