package com.productdelivery.core.dtos.employee;

import com.productdelivery.core.entities.Employee;

import java.time.LocalDateTime;

public record EmployeeResponseDto(
    String id,
    String name,
    LocalDateTime createdAt
) {

   public EmployeeResponseDto(Employee employee) {
       this(
           employee.getId(),
           employee.getName(),
           employee.getCreatedAt()
       );
   }
}
