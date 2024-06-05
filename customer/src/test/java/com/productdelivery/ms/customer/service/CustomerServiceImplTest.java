package com.productdelivery.ms.customer.service;

import com.productdelivery.core.dtos.customer.CustomerRequestDto;
import com.productdelivery.core.dtos.customer.CustomerResponseDto;
import com.productdelivery.core.entities.Customer;
import com.productdelivery.core.exceptions.ResourceNotFoundException;
import com.productdelivery.core.repositories.ICustomerRepository;
import com.productdelivery.ms.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getCustomer_Success() {
        Customer customer = createCustomerTest();
        CustomerResponseDto resultExpected = CustomerResponseDto.modelToDto(customer);
        setRepositoryReturnFindById(customer);

        CustomerResponseDto result = customerService.getCustomerById(customer.getId());

        verify(customerRepository, times(1)).findById(customer.getId());
        assertNotNull(result);
        assertEquals(resultExpected, result);
    }

    private Customer createCustomerTest() {
        return new Customer(
            UUID.randomUUID().toString(),
            "Gabriel",
            "gabriel@email.com",
            null,
            LocalDateTime.now()
        );
    }

    private void setRepositoryReturnFindById(Customer customer) {
        when(customerRepository.findById(customer.getId()))
            .thenReturn(Optional.of(customer));
    }

    @Test
    void getCustomer_Failure() {
        assertThrows(ResourceNotFoundException.class, () ->
            customerService.getCustomerById(UUID.randomUUID().toString()));
    }

    @Test
    void saveCustomer_Success() {
        String customerId = UUID.randomUUID().toString();
        LocalDateTime customerCreatedAt = LocalDateTime.now();
        CustomerResponseDto expectedResult = CustomerResponseDto.builder()
            .id(customerId)
            .name("Gilson")
            .email("gilson@email.com")
            .createdAt(customerCreatedAt)
            .build();

        CustomerRequestDto customerToSave = new CustomerRequestDto(expectedResult.name(), expectedResult.email());

        when(customerRepository.save(any()))
            .thenAnswer(invocation -> {
                Customer customer = invocation.getArgument(0);
                customer.setId(customerId);
                customer.setCreatedAt(customerCreatedAt);
                return customer;
            });

        CustomerResponseDto result = customerService.saveCustomer(customerToSave);

        verify(customerRepository, times(1)).save(any());
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    void updateCustomer_Success() {
        Customer customer = createCustomerTest();
        setRepositoryReturnFindById(customer);

        CustomerRequestDto customerToUpdate = new CustomerRequestDto("Eduardo", "edu@eml.com");
        customerService.updateCustomerById(customerToUpdate, customer.getId());

        verify(customerRepository, times(1)).save(customer);
        verify(customerRepository, times(1)).findById(customer.getId());
        assertEquals(customerToUpdate.name(), customer.getName());
        assertEquals(customerToUpdate.email(), customer.getEmail());
    }

    @Test
    void deleteCustomer_Success() {
        Customer customer = createCustomerTest();
        setRepositoryReturnFindById(customer);

        customerService.deleteCustomerById(customer.getId());

        verify(customerRepository, times(1)).findById(customer.getId());
        verify(customerRepository, times(1)).delete(customer);
    }
}