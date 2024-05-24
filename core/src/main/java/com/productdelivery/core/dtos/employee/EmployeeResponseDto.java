package com.productdelivery.core.dtos.employee;

import com.productdelivery.core.entities.Employee;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EmployeeResponseDto(
    String id,
    String name,
    String registryNumber,
    LocalDateTime createdAt
) {

   public static EmployeeResponseDto modelToDto(Employee employee) {
       return EmployeeResponseDto.builder()
           .id(employee.getId())
           .name(employee.getName())
           .registryNumber(employee.getRegistryNumber())
           .createdAt(employee.getCreatedAt())
           .build();
   }
}
