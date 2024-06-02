package com.productdelivery.ms.employee.controller;

import com.productdelivery.core.dtos.employee.EmployeeRequestDto;
import com.productdelivery.core.dtos.employee.EmployeeResponseDto;
import com.productdelivery.ms.employee.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable String employeeId) {
        EmployeeResponseDto employeeFound = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeFound);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> saveEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        EmployeeResponseDto employeeResponseDto = employeeService.saveEmployee(employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponseDto);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(
        @RequestBody @Valid EmployeeRequestDto employeeRequestDto,
        @PathVariable String employeeId
    ) {
        employeeService.updateEmployee(employeeRequestDto, employeeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}
