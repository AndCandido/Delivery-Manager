package com.productdelivery.ms.customer.service.impl;

import com.productdelivery.core.dtos.customer.CustomerRequestDto;
import com.productdelivery.core.dtos.customer.CustomerResponseDto;
import com.productdelivery.core.entities.Customer;
import com.productdelivery.core.exceptions.ResourceNotFoundException;
import com.productdelivery.core.repositories.ICustomerRepository;
import com.productdelivery.ms.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public CustomerResponseDto getCustomerById(String customerId) {
        Customer customerFound = findCustomerById(customerId);
        return CustomerResponseDto.modelToDto(customerFound);
    }

    private Customer findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer does not exist"));
    }

    @Override
    public CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDto, customer);
        customerRepository.save(customer);
        return CustomerResponseDto.modelToDto(customer);
    }

    @Override
    public void updateCustomerById(CustomerRequestDto customerRequestDto, String customerId) {
        Customer customerFound = findCustomerById(customerId);
        BeanUtils.copyProperties(customerRequestDto, customerFound);
        customerRepository.save(customerFound);
    }

    @Override
    public void deleteCustomerById(String customerId) {
        Customer customerById = findCustomerById(customerId);
        customerRepository.delete(customerById);
    }
}
