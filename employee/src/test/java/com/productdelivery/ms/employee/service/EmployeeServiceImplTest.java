package com.productdelivery.ms.employee.service;

import com.productdelivery.core.dtos.employee.EmployeeRequestDto;
import com.productdelivery.core.dtos.employee.EmployeeResponseDto;
import com.productdelivery.core.entities.Employee;
import com.productdelivery.core.exceptions.ResourceNotFoundException;
import com.productdelivery.core.repositories.IEmployeeRepository;
import com.productdelivery.ms.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private IEmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getEmployeeById_Success() {
        Employee employee = createEmployeeTest();
        EmployeeResponseDto expectedResult = EmployeeResponseDto.modelToDto(employee);
        setRepositoryFindById(employee);

        EmployeeResponseDto result = employeeService.getEmployeeById(employee.getId());

        verify(employeeRepository, times(1)).findById(employee.getId());

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    private Employee createEmployeeTest() {
        return new Employee("27c74370-1a61-4c0b-8148-662a7fcf64a3", "Guilherme", "31298379234", LocalDateTime.now());
    }

    private void setRepositoryFindById(Employee employee) {
        when(employeeRepository.findById(employee.getId()))
            .thenReturn(Optional.of(employee));
    }

    @Test
    void getEmployeeById_Failure_EmployeeNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById("27c74370-1a61-4c0b-8148-662a7fcf64a3");
        });
    }

    @Test
    void saveEmployee_Success() {
        String employeeId = "27c74370-1a61-4c0b-8148-662a7fcf64a3";
        LocalDateTime employeeCreatedAt = LocalDateTime.now();

        EmployeeRequestDto employeeRequestDto = createEmployeeRequestDto();
        EmployeeResponseDto expectedResult = createEmployeeResponseDto(employeeId, employeeCreatedAt, employeeRequestDto);

        when(employeeRepository.save(any())).thenAnswer(invocation -> {
            Employee employee = invocation.getArgument(0);
            employee.setId(employeeId);
            employee.setCreatedAt(employeeCreatedAt);
            return employee;
        });

        EmployeeResponseDto result = employeeService.saveEmployee(employeeRequestDto);

        verify(employeeRepository, times(1)).save(any());
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    private EmployeeResponseDto createEmployeeResponseDto(String employeeId, LocalDateTime employeeCreatedAt, EmployeeRequestDto employeeRequestDto) {
        return EmployeeResponseDto.builder()
            .id(employeeId)
            .name(employeeRequestDto.name())
            .registryNumber(employeeRequestDto.registryNumber())
            .createdAt(employeeCreatedAt)
            .build();
    }

    private EmployeeRequestDto createEmployeeRequestDto() {
        return EmployeeRequestDto.builder()
            .name("Juliano")
            .registryNumber("72346324")
            .build();
    }

    @Test
    void updateEmployee_Success() {
        Employee employeeTest = createEmployeeTest();
        setRepositoryFindById(employeeTest);

        EmployeeRequestDto employeeRequestDto = createEmployeeRequestDto();

        employeeService.updateEmployee(employeeRequestDto, employeeTest.getId());

        verify(employeeRepository, times(1)).findById(employeeTest.getId());
        verify(employeeRepository, times(1)).save(employeeTest);

        assertNotNull(employeeTest);
        assertNotNull(employeeTest.getId());
        assertNotNull(employeeTest.getCreatedAt());
        assertEquals(employeeTest.getName(), employeeRequestDto.name());
        assertEquals(employeeTest.getRegistryNumber(), employeeRequestDto.registryNumber());
    }

    @Test
    void deleteEmployee_Success() {
        Employee employeeTest = createEmployeeTest();
        setRepositoryFindById(employeeTest);

        employeeService.deleteEmployee(employeeTest.getId());

        verify(employeeRepository, times(1)).findById(employeeTest.getId());
        verify(employeeRepository, times(1)).delete(employeeTest);
    }
}