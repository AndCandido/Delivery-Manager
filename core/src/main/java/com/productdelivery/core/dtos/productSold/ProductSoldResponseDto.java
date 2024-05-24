package com.productdelivery.core.dtos.productSold;

import com.productdelivery.core.entities.ProductSold;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductSoldResponseDto(
    String id,
    String ref,
    String name,
    int quantitySold,
    LocalDateTime createdAt
) {
    public static ProductSoldResponseDto modelToDto(ProductSold productSold) {
        return ProductSoldResponseDto.builder()
            .id(productSold.getId())
            .ref(productSold.getRef())
            .name(productSold.getName())
            .quantitySold(productSold.getQuantitySold())
            .createdAt(productSold.getCreatedAt())
            .build();
    }

    public static List<ProductSoldResponseDto> modelToDtoList(List<ProductSold> productsSold) {
        return productsSold.stream().map(ProductSoldResponseDto::modelToDto).toList();
    }
}
