package com.productdelivery.core.dtos.customer;

import com.productdelivery.core.dtos.order.OrderResponseDto;
import com.productdelivery.core.entities.Customer;
import lombok.Builder;

import java.util.List;

@Builder
public record CustomerOrdersResponseDto(
    String customerId,
    List<OrderResponseDto> orders
) {
    public static CustomerOrdersResponseDto modelToDto(Customer customer) {
        List<OrderResponseDto> ordersDto =
            OrderResponseDto.modelToDtoList(customer.getOrders());

        return CustomerOrdersResponseDto.builder()
            .customerId(customer.getId())
            .orders(ordersDto)
            .build();
    }
}
