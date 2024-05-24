package com.productdelivery.core.repositories;

import com.productdelivery.core.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, String> {
}
