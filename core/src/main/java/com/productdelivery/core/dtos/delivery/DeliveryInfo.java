package com.productdelivery.core.dtos.delivery;

import com.productdelivery.core.dtos.employee.EmployeeResponseDto;

import java.time.LocalDateTime;

public record DeliveryInfo(
    boolean isDelivered,
    EmployeeResponseDto deliveryman,
    LocalDateTime deliveryDate
) {
}
