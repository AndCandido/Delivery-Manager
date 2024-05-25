package com.productdelivery.ms.employee.service.impl;

import com.productdelivery.core.application.util.CopyPropertiesUtil;
import com.productdelivery.core.dtos.employee.EmployeeRequestDto;
import com.productdelivery.core.dtos.employee.EmployeeResponseDto;
import com.productdelivery.core.entities.Employee;
import com.productdelivery.core.exceptions.ResourceNotFoundException;
import com.productdelivery.ms.employee.service.IEmployeeService;
import com.productdelivery.core.repositories.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto getEmployeeById(String employeeId) {
        Employee employeeFound = findEmployeeById(employeeId);
        return EmployeeResponseDto.modelToDto(employeeFound);
    }

    private Employee findEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("employee does not exists"));
    }

    @Override
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, newEmployee);
        employeeRepository.save(newEmployee);
        return EmployeeResponseDto.modelToDto(newEmployee);
    }

    @Override
    public void updateEmployee(EmployeeRequestDto employeeRequestDto, String employeeId) {
        Employee employeeFound = findEmployeeById(employeeId);
        CopyPropertiesUtil.copyNonNullProperties(employeeRequestDto, employeeFound);
        employeeRepository.save(employeeFound);
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Employee employeeFound = findEmployeeById(employeeId);
        employeeRepository.delete(employeeFound);
    }
}
