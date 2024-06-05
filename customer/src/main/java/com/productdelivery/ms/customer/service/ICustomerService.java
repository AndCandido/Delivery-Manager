package com.productdelivery.ms.customer.service;

import com.productdelivery.core.dtos.customer.CustomerOrdersResponseDto;
import com.productdelivery.core.dtos.customer.CustomerRequestDto;
import com.productdelivery.core.dtos.customer.CustomerResponseDto;

public interface ICustomerService {
    CustomerResponseDto getCustomerById(String customerId);

    CustomerOrdersResponseDto getCustomerOrdersById(String customerId);

    CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto);

    void updateCustomerById(CustomerRequestDto customerRequestDto, String customerId);

    void deleteCustomerById(String customerId);
}
