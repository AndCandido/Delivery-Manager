package com.productdelivery.ms.customer.controller;

import com.productdelivery.core.dtos.customer.CustomerRequestDto;
import com.productdelivery.core.dtos.customer.CustomerResponseDto;
import com.productdelivery.ms.customer.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable String customerId) {
        CustomerResponseDto customerFoundDto = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerFoundDto);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> saveCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        CustomerResponseDto customerSavedDto = customerService.saveCustomer(customerRequestDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(customerSavedDto);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomerById(
        @RequestBody @Valid CustomerRequestDto customerRequestDto,
        @PathVariable String customerId
    ) {
        customerService.updateCustomerById(customerRequestDto, customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
