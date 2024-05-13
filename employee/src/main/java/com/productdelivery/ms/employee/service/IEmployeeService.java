package com.productdelivery.ms.employee.service;

import com.productdelivery.core.dtos.employee.EmployeeRequestDto;
import com.productdelivery.core.dtos.employee.EmployeeResponseDto;

public interface IEmployeeService {

    EmployeeResponseDto getEmployeeById(String employeeId);
    EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto);
    void updateEmployee(EmployeeRequestDto employeeRequestDto, String employeeId);
    void deleteEmployee(String employeeId);
}
