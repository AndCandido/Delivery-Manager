package com.productdelivery.core.dtos.order;

import com.productdelivery.core.dtos.customer.CustomerResponseDto;
import com.productdelivery.core.dtos.delivery.DeliveryInfo;
import com.productdelivery.core.dtos.productSold.ProductSoldRequestDto;
import com.productdelivery.core.entities.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDto(

    @NotNull(message = "{order.customer.not-null}")
    CustomerResponseDto customer,

    @NotEmpty(message = "{order.productsSold.not-empty}")
    List<ProductSoldRequestDto> productsSold,

    @NotNull(message = "{order.deliveryAddress.not-null}")
    @Valid
    Address deliveryAddress,

    DeliveryInfo deliveryInfo
) {
}
