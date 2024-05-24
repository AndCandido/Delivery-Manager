package com.productdelivery.core.dtos.order;

import com.productdelivery.core.dtos.customer.CustomerResponseDto;
import com.productdelivery.core.dtos.delivery.DeliveryInfo;
import com.productdelivery.core.dtos.productSold.ProductSoldResponseDto;
import com.productdelivery.core.entities.Address;
import com.productdelivery.core.entities.Order;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponseDto(
    String id,
    CustomerResponseDto customerResponseDto,
    List<ProductSoldResponseDto> productsSold,
    Address deliveryAddress,
    DeliveryInfo deliveryInfo,
    LocalDateTime createdAt

) {
    public static OrderResponseDto modelToDto(Order order) {
        List<ProductSoldResponseDto> productSoldResponseDtos =
            ProductSoldResponseDto.modelToDtoList(order.getProductsSold());

        CustomerResponseDto customerResponseDto =
            CustomerResponseDto.modelToDto(order.getCustomer());

        return OrderResponseDto.builder()
            .id(order.getId())
            .customerResponseDto(customerResponseDto)
            .productsSold(productSoldResponseDtos)
            .deliveryAddress(order.getDeliveryAddress())
            .createdAt(order.getCreatedAt())
            .build();
    }

    public static List<OrderResponseDto> modelToDtoList(List<Order> orders) {
        return orders.stream().map(OrderResponseDto::modelToDto).toList();
    }
}
